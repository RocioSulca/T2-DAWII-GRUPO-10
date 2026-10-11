package com.pagoya.resolucion.client;

import com.pagoya.resolucion.dto.ReclamoDto;
import com.pagoya.resolucion.dto.ReclamoUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// "ms-reclamos" es el nombre registrado en Eureka; Feign lo resuelve por balanceo de carga.
@FeignClient(name = "ms-reclamos")
public interface ReclamoClient {

	@GetMapping("/reclamos/{numreclamo}")
	ReclamoDto consultar(@PathVariable("numreclamo") String numreclamo);

	@PutMapping("/reclamos/{numreclamo}")
	ReclamoDto actualizar(@PathVariable("numreclamo") String numreclamo,
						  @RequestBody ReclamoUpdateDto request);
}
