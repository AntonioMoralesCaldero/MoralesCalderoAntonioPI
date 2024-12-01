//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import jakarta.persistence.Column;

public class VehiculoModel {

    private int id;
    private String modelo;
    private String color;
    private float precio;
    private int potencia;
    private String imagen;
    private String matricula;
    private boolean vendido = false;

    public VehiculoModel() {
    }

	public VehiculoModel(int id, String modelo, String color, float precio, int potencia, String imagen,
			String matricula, boolean vendido) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		this.potencia = potencia;
		this.imagen = imagen;
		this.matricula = matricula;
		this.vendido = vendido;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

    
}
