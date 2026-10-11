package com.pagoya.reclamos.entidad;

import java.time.LocalDate;

public class Reclamo {

	private String numreclamo;
	private LocalDate fechareclamo;
	private String nomcliente;
	private String ultimos4tarjeta;
	private String motivo;
	private String nombreAnalista = "No asignado";
	private Integer estado = 1;        // 1 = pendiente, 2 = procedente, 3 = improcedente
	private String observacion = "por evaluar";

	public Reclamo() {
	}

	public Reclamo(String numreclamo, LocalDate fechareclamo, String nomcliente,
				   String ultimos4tarjeta, String motivo) {
		this.numreclamo = numreclamo;
		this.fechareclamo = fechareclamo;
		this.nomcliente = nomcliente;
		this.ultimos4tarjeta = ultimos4tarjeta;
		this.motivo = motivo;
	}

	public String getNumreclamo() { return numreclamo; }
	public void setNumreclamo(String numreclamo) { this.numreclamo = numreclamo; }

	public LocalDate getFechareclamo() { return fechareclamo; }
	public void setFechareclamo(LocalDate fechareclamo) { this.fechareclamo = fechareclamo; }

	public String getNomcliente() { return nomcliente; }
	public void setNomcliente(String nomcliente) { this.nomcliente = nomcliente; }

	public String getUltimos4tarjeta() { return ultimos4tarjeta; }
	public void setUltimos4tarjeta(String ultimos4tarjeta) { this.ultimos4tarjeta = ultimos4tarjeta; }

	public String getMotivo() { return motivo; }
	public void setMotivo(String motivo) { this.motivo = motivo; }

	public String getNombreAnalista() { return nombreAnalista; }
	public void setNombreAnalista(String nombreAnalista) { this.nombreAnalista = nombreAnalista; }

	public Integer getEstado() { return estado; }
	public void setEstado(Integer estado) { this.estado = estado; }

	public String getObservacion() { return observacion; }
	public void setObservacion(String observacion) { this.observacion = observacion; }
}
