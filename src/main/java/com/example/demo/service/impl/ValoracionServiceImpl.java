// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Valoracion;
import com.example.demo.model.ValoracionModel;
import com.example.demo.repository.ValoracionRepository;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ValoracionModel guardarValoracion(ValoracionModel valoracionModel) {
        Valoracion valoracion = new Valoracion();

        valoracion.setComentario(valoracionModel.getComentario());
        valoracion.setPuntuacion(valoracionModel.getPuntuacion());

        valoracion.setCita(citaRepository.findById(valoracionModel.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada")));

        valoracion.setUsuario(usuarioRepository.findById(valoracionModel.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        valoracion = valoracionRepository.save(valoracion);

        return convertToModel(valoracion);
    }

    @Override
    public ValoracionModel obtenerValoracionPorId(int id) {
        return valoracionRepository.findById(id)
                .map(this::convertToModel)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));
    }

    @Override
    public List<ValoracionModel> obtenerValoracionesPorUsuario(int usuarioId) {
        return valoracionRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ValoracionModel> obtenerTodasValoraciones() {
        return valoracionRepository.findAll()
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    private ValoracionModel convertToModel(Valoracion valoracion) {
        return new ValoracionModel(
                valoracion.getId(),
                valoracion.getComentario(),
                valoracion.getPuntuacion(),
                valoracion.getCita().getId(),
                valoracion.getUsuario().getId()
        );
    }
}
