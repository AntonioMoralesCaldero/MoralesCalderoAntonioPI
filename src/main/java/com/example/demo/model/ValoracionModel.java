//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

public class ValoracionModel {

    private int id;
    private String comentario;
    private int puntuacion;
    private int citaId;
    private int usuarioId;

    public ValoracionModel() {
    }

    public ValoracionModel(int id, String comentario, int puntuacion, int citaId, int usuarioId) {
        this.id = id;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.citaId = citaId;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getCitaId() {
        return citaId;
    }

    public void setCitaId(int citaId) {
        this.citaId = citaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
