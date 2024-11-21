package com.example.demo.model;

public class ValoracionModel {
	
    private int id;
    private int usuarioId;
    private int citaId;
    private int estrellas;
    private String comentario;
    
    public ValoracionModel() {
    	
    }

	public ValoracionModel(int id, int usuarioId, int citaId, int estrellas, String comentario) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.citaId = citaId;
		this.estrellas = estrellas;
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getCitaId() {
		return citaId;
	}

	public void setCitaId(int citaId) {
		this.citaId = citaId;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
    
    

}
