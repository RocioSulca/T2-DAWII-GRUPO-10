package com.pagoya.resolucion.negocio;

import org.springframework.http.HttpStatus;

public class ResolucionException extends RuntimeException {

	private final HttpStatus status;

	public ResolucionException(HttpStatus status, String mensaje) {
		super(mensaje);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
