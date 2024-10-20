//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.util.Date;

public class VehiculoModel {

    private int id;

    private String marca;
    
    private String modelo;
    
    private Date fecha_fabricacion;
    
    private String color;
    
    private float precio;
    
    private String estado;
    
    private TipoModel tipo;
    
    public VehiculoModel() {
    	
    }

	public VehiculoModel(int id, String marca, String modelo, Date fecha_fabricacion, String color, float precio,
			String estado, TipoModel tipo) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.fecha_fabricacion = fecha_fabricacion;
		this.color = color;
		this.precio = precio;
		this.estado = estado;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getFecha_fabricacion() {
		return fecha_fabricacion;
	}

	public void setFecha_fabricacion(Date fecha_fabricacion) {
		this.fecha_fabricacion = fecha_fabricacion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoModel getTipo() {
		return tipo;
	}

	public void setTipo(TipoModel tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "VehiculoModel [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", fecha_fabricacion="
				+ fecha_fabricacion + ", color=" + color + ", precio=" + precio + ", estado=" + estado + ", tipo="
				+ tipo + "]";
	}
      
    
}
