package com.pagoya.resolucion.dto;

// Respuesta de exito (y de error, con reclamo = null)
public record ResolucionResponse(
		String mensaje,
		ReclamoDto reclamo
) {
}
