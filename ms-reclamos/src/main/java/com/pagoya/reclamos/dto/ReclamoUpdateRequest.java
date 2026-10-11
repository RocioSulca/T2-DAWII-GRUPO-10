package com.pagoya.reclamos.dto;

// Cuerpo para actualizar un reclamo (PUT /reclamos/{numreclamo}).
// Lo usa ms-resolucion via OpenFeign. Los campos nulos no se modifican.
public record ReclamoUpdateRequest(
		String nombreAnalista,
		Integer estado,
		String observacion
) {
}
