package com.example.demo.service;

import com.example.demo.model.CitaModel;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    CitaModel crearCita(CitaModel citaModel);
    void cancelarCita(int citaId);
    List<CitaModel> obtenerCitasPorUsuario(int usuarioId);
    CitaModel obtenerCitaPorId(int citaId);
    void actualizarCita(CitaModel citaModel);
    List<CitaModel> obtenerTodasCitas();
    
}

