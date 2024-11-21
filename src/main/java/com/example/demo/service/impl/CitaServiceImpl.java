package com.example.demo.service.impl;

import com.example.demo.entity.Cita;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Vehiculo;
import com.example.demo.model.CitaModel;
import com.example.demo.model.UsuarioModel;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public CitaModel crearCita(CitaModel citaModel) {
        Cita cita = new Cita();
        cita.setProblema(citaModel.getProblema());
        cita.setFechaCita(citaModel.getFechaCita());
        cita.setEstado("pendiente");

        // Validar y asignar usuario
        Usuario usuario = usuarioRepository.findById(citaModel.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        cita.setUsuario(usuario);

        // Validar y asignar vehículo de ocasión (opcional)
        if (citaModel.getVehiculoOcasionId() != 0) {
            Vehiculo vehiculoOcasion = vehiculoRepository.findById(citaModel.getVehiculoOcasionId())
                    .orElseThrow(() -> new RuntimeException("Vehículo de ocasión no encontrado"));
            cita.setVehiculoOcasion(vehiculoOcasion);
        }

        // Guardar cita y convertir a modelo
        cita = citaRepository.save(cita);
        return convertToModel(cita);
    }

    @Override
    public void cancelarCita(int citaId) {
        citaRepository.deleteById(citaId);
    }

    @Override
    public List<CitaModel> obtenerCitasPorUsuario(int usuarioId) {
        return citaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public CitaModel obtenerCitaPorId(int citaId) {
        return citaRepository.findById(citaId)
                .map(this::convertToModel)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Override
    public void actualizarCita(CitaModel citaModel) {
        Cita cita = citaRepository.findById(citaModel.getId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // Actualizar campos
        cita.setDiagnostico(citaModel.getDiagnostico());
        cita.setEstado(citaModel.getEstado());

        // Validar y asignar fecha de reparación
        if (citaModel.getFechaReparacionFinalizada() != null) {
            cita.setFechaReparacionFinalizada(citaModel.getFechaReparacionFinalizada());
        }

        // Validar y asignar vehículo de ocasión (opcional)
        if (citaModel.getVehiculoOcasionId() != 0) {
            Vehiculo vehiculoOcasion = vehiculoRepository.findById(citaModel.getVehiculoOcasionId())
                    .orElseThrow(() -> new RuntimeException("Vehículo de ocasión no encontrado"));
            cita.setVehiculoOcasion(vehiculoOcasion);
        }

        // Guardar cambios
        citaRepository.save(cita);
    }

    @Override
    public List<CitaModel> obtenerTodasCitas() {
        return citaRepository.findAll()
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    private CitaModel convertToModel(Cita cita) {
        return new CitaModel(
                cita.getId(),
                cita.getProblema(),
                cita.getFechaCita(),
                cita.getEstado(),
                cita.getDiagnostico(),
                cita.getFechaReparacionFinalizada(),
                new UsuarioModel(cita.getUsuario().getId(), cita.getUsuario().getUsername()),
                cita.getVehiculoOcasion() != null ? cita.getVehiculoOcasion().getId() : 0
        );
    }
}

