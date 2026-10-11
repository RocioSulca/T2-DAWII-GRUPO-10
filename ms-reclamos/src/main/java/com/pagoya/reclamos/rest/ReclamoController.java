package com.pagoya.reclamos.rest;

import com.pagoya.reclamos.dto.ReclamoRequest;
import com.pagoya.reclamos.dto.ReclamoUpdateRequest;
import com.pagoya.reclamos.entidad.Reclamo;
import com.pagoya.reclamos.negocio.ReclamoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reclamos")
public class ReclamoController {

	private final ReclamoService service;

	public ReclamoController(ReclamoService service) {
		this.service = service;
	}

	// Registrar reclamo
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Reclamo registrar(@RequestBody ReclamoRequest request) {
		return service.registrar(request);
	}

	// Consultar por numero de reclamo
	@GetMapping("/{numreclamo}")
	public Reclamo consultar(@PathVariable String numreclamo) {
		return service.consultar(numreclamo);
	}

	// Actualizar reclamo (usado por ms-resolucion via OpenFeign)
	@PutMapping("/{numreclamo}")
	public Reclamo actualizar(@PathVariable String numreclamo,
							  @RequestBody ReclamoUpdateRequest request) {
		return service.actualizar(numreclamo, request);
	}
}
