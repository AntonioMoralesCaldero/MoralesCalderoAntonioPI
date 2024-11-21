// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Cita;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Valoracion;
import com.example.demo.model.ValoracionModel;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ValoracionRepository;
import com.example.demo.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public ValoracionModel agregarValoracion(ValoracionModel valoracionModel) {
        Usuario usuario = usuarioRepository.findById(valoracionModel.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Cita cita = citaRepository.findById(valoracionModel.getCitaId())
            .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        Valoracion valoracion = new Valoracion();
        valoracion.setUsuario(usuario);
        valoracion.setCita(cita);
        valoracion.setEstrellas(valoracionModel.getEstrellas());
        valoracion.setComentario(valoracionModel.getComentario());

        Valoracion valoracionGuardada = valoracionRepository.save(valoracion);

        ValoracionModel valoracionGuardadaModel = new ValoracionModel();
        valoracionGuardadaModel.setId(valoracionGuardada.getId());
        valoracionGuardadaModel.setEstrellas(valoracionGuardada.getEstrellas());
        valoracionGuardadaModel.setComentario(valoracionGuardada.getComentario());
        valoracionGuardadaModel.setUsuarioId(usuario.getId());
        valoracionGuardadaModel.setCitaId(cita.getId());

        return valoracionGuardadaModel;
    }
}
