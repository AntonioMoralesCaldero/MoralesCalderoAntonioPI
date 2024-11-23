// Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String problema;
    
    private LocalDateTime fechaCita;
    
    private String estado;
    
    private String diagnostico;
    
    private LocalDateTime fechaReparacionFinalizada;

    private boolean valorada;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Vehiculo vehiculoOcasion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public LocalDateTime getFechaReparacionFinalizada() {
        return fechaReparacionFinalizada;
    }

    public void setFechaReparacionFinalizada(LocalDateTime fechaReparacionFinalizada) {
        this.fechaReparacionFinalizada = fechaReparacionFinalizada;
    }

    public boolean isValorada() {
        return valorada;
    }

    public void setValorada(boolean valorada) {
        this.valorada = valorada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculoOcasion() {
        return vehiculoOcasion;
    }

    public void setVehiculoOcasion(Vehiculo vehiculoOcasion) {
        this.vehiculoOcasion = vehiculoOcasion;
    } 
}
