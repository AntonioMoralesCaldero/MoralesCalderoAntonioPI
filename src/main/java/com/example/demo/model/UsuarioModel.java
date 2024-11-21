//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.time.LocalDate;

public class UsuarioModel {
	
    private int id;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private String username;
    private String password;
    private boolean isActive = true;
    
    public UsuarioModel() {
    	
    }
    
    public UsuarioModel(int id, String username) {
        this.id = id;
        this.username = username;
    }


    public UsuarioModel(int id, String nombre, String apellidos, LocalDate fecha_nacimiento, String direccion, String username,
	String password, boolean isActive) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate fecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setfecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nacimiento="
				+ fecha_nacimiento + ", direccion=" + direccion + ", username=" + username + ", password=" + password
				+ ", isActive=" + isActive + "]";
	}
    
    

}