package com.pagoya.resolucion.dto;

// Cuerpo enviado a ms-reclamos (PUT /reclamos/{numreclamo})
public record ReclamoUpdateDto(
		String nombreAnalista,
		Integer estado,
		String observacion
) {
}
