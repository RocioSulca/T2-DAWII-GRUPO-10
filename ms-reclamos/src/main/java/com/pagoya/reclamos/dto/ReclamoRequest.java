package com.pagoya.reclamos.dto;

import java.time.LocalDate;

// Cuerpo para registrar un reclamo (POST /reclamos)
public record ReclamoRequest(
		String numreclamo,
		LocalDate fechareclamo,
		String nomcliente,
		String ultimos4tarjeta,
		String motivo
) {
}
