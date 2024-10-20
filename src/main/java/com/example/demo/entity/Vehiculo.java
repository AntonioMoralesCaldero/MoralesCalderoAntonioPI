//Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Vehiculo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marca", length = 30)
    private String marca;
    
    @Column(name = "modelo", length = 30)
    private String modelo;
    
    @Column(name = "fecha_fabricacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_fabricacion;
    
    @Column(name = "color", length = 30)
    private String color;
    
    @Column(name = "precio", length = 30)
    private float precio;
    
    @Column(name = "estado", length = 30)
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "idTipo")
    private Tipo tipo;

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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
    
    
    
}
