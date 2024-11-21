// Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.time.LocalDate;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Vehiculo;


public class CompraModel {
	
	private int id;	
	private Usuario usuario;	
    private Vehiculo vehiculo;
    private LocalDate fechaCompra;

	public CompraModel(int id, Usuario usuario, Vehiculo vehiculo, LocalDate fechaCompra) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.fechaCompra = fechaCompra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Override
	public String toString() {
		return "CompraModel [id=" + id + ", usuario=" + usuario + ", vehiculo=" + vehiculo + ", fechaCompra="
				+ fechaCompra + "]";
	}
    
     

}
