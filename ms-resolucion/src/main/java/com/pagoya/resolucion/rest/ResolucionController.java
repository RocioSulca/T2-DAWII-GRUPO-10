package com.pagoya.resolucion.rest;

import com.pagoya.resolucion.dto.ResolucionRequest;
import com.pagoya.resolucion.dto.ResolucionResponse;
import com.pagoya.resolucion.negocio.ResolucionException;
import com.pagoya.resolucion.negocio.ResolucionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/resoluciones")
public class ResolucionController {

	private final ResolucionService service;

	public ResolucionController(ResolucionService service) {
		this.service = service;
	}

	// Resolver reclamo: estado 2 = procedente, 3 = improcedente
	@PostMapping
	public ResponseEntity<ResolucionResponse> resolver(@RequestBody ResolucionRequest request) {
		return ResponseEntity.ok(service.resolver(request));
	}

	// Mensajes de error con formato JSON uniforme
	@ExceptionHandler(ResolucionException.class)
	public ResponseEntity<Map<String, Object>> manejarError(ResolucionException e) {
		HttpStatus status = e.getStatus();
		return ResponseEntity.status(status).body(Map.of(
				"mensaje", e.getMessage(),
				"status", status.value()
		));
	}
}
