package com.pagoya.reclamos.repositorio;

import com.pagoya.reclamos.entidad.Reclamo;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Almacenamiento en memoria (sin base de datos), suficiente para la evaluacion.
@Repository
public class ReclamoRepository {

	private final Map<String, Reclamo> almacen = new ConcurrentHashMap<>();

	public Reclamo save(Reclamo reclamo) {
		almacen.put(reclamo.getNumreclamo(), reclamo);
		return reclamo;
	}

	public Optional<Reclamo> findByNumreclamo(String numreclamo) {
		return Optional.ofNullable(almacen.get(numreclamo));
	}

	public boolean existsByNumreclamo(String numreclamo) {
		return almacen.containsKey(numreclamo);
	}
}
