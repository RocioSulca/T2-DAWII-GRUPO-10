package com.pagoya.resolucion.negocio;

import com.pagoya.resolucion.client.ReclamoClient;
import com.pagoya.resolucion.dto.ReclamoDto;
import com.pagoya.resolucion.dto.ReclamoUpdateDto;
import com.pagoya.resolucion.dto.ResolucionRequest;
import com.pagoya.resolucion.dto.ResolucionResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResolucionService {

	public static final int PROCEDENTE = 2;
	public static final int IMPROCEDENTE = 3;

	private final ReclamoClient reclamoClient;

	public ResolucionService(ReclamoClient reclamoClient) {
		this.reclamoClient = reclamoClient;
	}

	public ResolucionResponse resolver(ResolucionRequest request) {
		validar(request);

		// 1. Validar existencia del reclamo en ms-reclamos (via OpenFeign)
		ReclamoDto existente = consultarReclamo(request.numreclamo());

		// 2. Actualizar estado, analista y observacion segun el resultado
		ReclamoUpdateDto update = new ReclamoUpdateDto(
				request.nombreAnalista(),
				request.estado(),
				observacionPara(request.estado())
		);
		ReclamoDto actualizado = actualizarReclamo(existente.numreclamo(), update);

		String mensaje = request.estado() == PROCEDENTE
				? "Reclamo " + actualizado.numreclamo() + " resuelto como PROCEDENTE"
				: "Reclamo " + actualizado.numreclamo() + " resuelto como IMPROCEDENTE";
		return new ResolucionResponse(mensaje, actualizado);
	}

	private void validar(ResolucionRequest request) {
		if (request.numreclamo() == null || request.numreclamo().isBlank()) {
			throw new ResolucionException(HttpStatus.BAD_REQUEST, "numreclamo es obligatorio");
		}
		if (request.nombreAnalista() == null || request.nombreAnalista().isBlank()) {
			throw new ResolucionException(HttpStatus.BAD_REQUEST, "nombreAnalista es obligatorio");
		}
		if (request.estado() == null || (request.estado() != PROCEDENTE && request.estado() != IMPROCEDENTE)) {
			throw new ResolucionException(HttpStatus.BAD_REQUEST,
					"estado invalido: use 2 (procedente) o 3 (improcedente)");
		}
	}

	private ReclamoDto consultarReclamo(String numreclamo) {
		try {
			return reclamoClient.consultar(numreclamo);
		} catch (FeignException.NotFound e) {
			throw new ResolucionException(HttpStatus.NOT_FOUND,
					"El reclamo " + numreclamo + " no existe");
		} catch (FeignException e) {
			throw new ResolucionException(HttpStatus.SERVICE_UNAVAILABLE,
					"Error al consultar ms-reclamos: " + e.status());
		} catch (RuntimeException e) {
			throw new ResolucionException(HttpStatus.SERVICE_UNAVAILABLE,
					"ms-reclamos no disponible");
		}
	}

	private ReclamoDto actualizarReclamo(String numreclamo, ReclamoUpdateDto update) {
		try {
			return reclamoClient.actualizar(numreclamo, update);
		} catch (FeignException e) {
			throw new ResolucionException(HttpStatus.SERVICE_UNAVAILABLE,
					"No se pudo actualizar el reclamo en ms-reclamos: " + e.status());
		} catch (RuntimeException e) {
			throw new ResolucionException(HttpStatus.SERVICE_UNAVAILABLE,
					"ms-reclamos no disponible");
		}
	}

	private String observacionPara(int estado) {
		return estado == PROCEDENTE
				? "Reclamo procedente: se aprueba la devolucion del consumo"
				: "Reclamo improcedente: el consumo fue validado por el cliente";
	}
}
