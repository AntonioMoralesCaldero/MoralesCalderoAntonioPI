// Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.util.Date;

public class CitaModel {
	
    private int id;

    private UsuarioModel usuario;

    private AdministradorModel administrador;

    private Date fecha;

    private String observaciones;
    
    private String diagnostico;
    
    public CitaModel() {
    	
    }

	public CitaModel(int id, UsuarioModel usuario, AdministradorModel administrador, Date fecha, String observaciones,
			String diagnostico) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.administrador = administrador;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.diagnostico = diagnostico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public AdministradorModel getAdministrador() {
		return administrador;
	}

	public void setAdministrador(AdministradorModel administrador) {
		this.administrador = administrador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	@Override
	public String toString() {
		return "CitaModel [id=" + id + ", usuario=" + usuario + ", administrador=" + administrador + ", fecha=" + fecha
				+ ", observaciones=" + observaciones + ", diagnostico=" + diagnostico + "]";
	}

    
}
