package com.pagoya.reclamos.negocio;

import com.pagoya.reclamos.dto.ReclamoRequest;
import com.pagoya.reclamos.dto.ReclamoUpdateRequest;
import com.pagoya.reclamos.entidad.Reclamo;
import com.pagoya.reclamos.repositorio.ReclamoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ReclamoService {

	private final ReclamoRepository repository;

	public ReclamoService(ReclamoRepository repository) {
		this.repository = repository;
	}

	public Reclamo registrar(ReclamoRequest request) {
		if (request.numreclamo() == null || request.numreclamo().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "numreclamo es obligatorio");
		}
		if (repository.existsByNumreclamo(request.numreclamo())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"Ya existe un reclamo con numreclamo " + request.numreclamo());
		}
		Reclamo reclamo = new Reclamo(
				request.numreclamo(),
				request.fechareclamo() != null ? request.fechareclamo() : LocalDate.now(),
				request.nomcliente(),
				request.ultimos4tarjeta(),
				request.motivo()
		);
		return repository.save(reclamo);
	}

	public Reclamo consultar(String numreclamo) {
		return repository.findByNumreclamo(numreclamo)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Reclamo no encontrado: " + numreclamo));
	}

	public Reclamo actualizar(String numreclamo, ReclamoUpdateRequest request) {
		Reclamo reclamo = consultar(numreclamo);
		if (request.nombreAnalista() != null) {
			reclamo.setNombreAnalista(request.nombreAnalista());
		}
		if (request.estado() != null) {
			reclamo.setEstado(request.estado());
		}
		if (request.observacion() != null) {
			reclamo.setObservacion(request.observacion());
		}
		return repository.save(reclamo);
	}
}
