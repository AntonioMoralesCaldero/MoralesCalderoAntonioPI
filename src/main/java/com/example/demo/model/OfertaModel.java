// Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

public class OfertaModel {

    private int id;
    private String modelo;
    private String color;
    private float precio;
    private int potencia;
    private String imagen;
    private String estado;
    private String matricula;
    
    public OfertaModel() {
    	
    }

	public OfertaModel(int id, String modelo, String color, float precio, int potencia, String imagen, String estado,
			String matricula) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		this.potencia = potencia;
		this.imagen = imagen;
		this.estado = estado;
		this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

    
}
