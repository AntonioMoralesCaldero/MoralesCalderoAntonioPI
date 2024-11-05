//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Vehiculo;
import com.example.demo.model.VehiculoModel;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<VehiculoModel> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        List<VehiculoModel> vehiculoModels = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            VehiculoModel model = new VehiculoModel(
                vehiculo.getId(),
                vehiculo.getModelo(),
                vehiculo.getColor(),
                vehiculo.getPrecio(),
                vehiculo.getPotencia(),
                vehiculo.getImagen()
            );
            vehiculoModels.add(model);
        }

        return vehiculoModels;
    }
    
    @Override
    public VehiculoModel getVehiculoById(int id) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id);
        if (optionalVehiculo.isPresent()) {
            Vehiculo vehiculo = optionalVehiculo.get();
            return new VehiculoModel(
                vehiculo.getId(),
                vehiculo.getModelo(),
                vehiculo.getColor(),
                vehiculo.getPrecio(),
                vehiculo.getPotencia(),
                vehiculo.getImagen()
            );
        } else {
            throw new RuntimeException("Vehículo no encontrado con el ID: " + id);
        }
    }
}
