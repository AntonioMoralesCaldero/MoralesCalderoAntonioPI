// Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.time.LocalDateTime;

public class CitaModel {

    private int id;
    private String problema;
    private LocalDateTime fechaCita;
    private String estado;
    private String diagnostico;
    private LocalDateTime fechaReparacionFinalizada;
    private UsuarioModel usuario;
    private Integer vehiculoOcasionId;
    private boolean valorada;

    public CitaModel() {
    }

    public CitaModel(int id, String problema, LocalDateTime fechaCita, String estado, String diagnostico,
                     LocalDateTime fechaReparacionFinalizada, UsuarioModel usuario, Integer vehiculoOcasionId, boolean valorada) {
        this.id = id;
        this.problema = problema;
        this.fechaCita = fechaCita;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.fechaReparacionFinalizada = fechaReparacionFinalizada;
        this.usuario = usuario;
        this.vehiculoOcasionId = vehiculoOcasionId;
        this.valorada = valorada;
    }

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

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Integer getVehiculoOcasionId() {
        return vehiculoOcasionId;
    }

    public void setVehiculoOcasionId(Integer vehiculoOcasionId) {
        this.vehiculoOcasionId = vehiculoOcasionId;
    }

    public boolean isValorada() {
        return valorada;
    }

    public void setValorada(boolean valorada) {
        this.valorada = valorada;
    }
}
