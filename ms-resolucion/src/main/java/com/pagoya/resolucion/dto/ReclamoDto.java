package com.pagoya.resolucion.dto;

import java.time.LocalDate;

// Representacion del reclamo que devuelve ms-reclamos
public record ReclamoDto(
		String numreclamo,
		LocalDate fechareclamo,
		String nomcliente,
		String ultimos4tarjeta,
		String motivo,
		String nombreAnalista,
		Integer estado,
		String observacion
) {
}
