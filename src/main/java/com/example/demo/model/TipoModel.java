//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

public class TipoModel {

    private int id;

    private String nombre;
    
    public TipoModel() {
    	
    }

    public TipoModel(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TipoModel [id=" + id + ", nombre=" + nombre + "]";
	}
    
    
}
