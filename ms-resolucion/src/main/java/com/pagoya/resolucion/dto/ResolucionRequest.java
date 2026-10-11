package com.pagoya.resolucion.dto;

// Cuerpo para resolver un reclamo (POST /resoluciones)
// estado: 2 = procedente, 3 = improcedente
public record ResolucionRequest(
		String numreclamo,
		String nombreAnalista,
		Integer estado
) {
}
