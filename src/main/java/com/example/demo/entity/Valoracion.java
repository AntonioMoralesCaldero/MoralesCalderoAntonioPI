//Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 500)
    private String comentario;

    @Column(nullable = false)
    private int puntuacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cita_id", referencedColumnName = "id")
    private Cita cita;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Valoracion() {
    }

    public Valoracion(int id, String comentario, int puntuacion, Cita cita, Usuario usuario) {
        this.id = id;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.cita = cita;
        this.usuario = usuario;
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

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
