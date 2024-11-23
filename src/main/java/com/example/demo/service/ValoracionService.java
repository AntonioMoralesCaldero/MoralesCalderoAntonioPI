// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.model.ValoracionModel;

import java.util.List;

public interface ValoracionService {	
    ValoracionModel guardarValoracion(ValoracionModel valoracionModel);
    ValoracionModel obtenerValoracionPorId(int id);
    List<ValoracionModel> obtenerValoracionesPorUsuario(int usuarioId);
    List<ValoracionModel> obtenerTodasValoraciones();
}
