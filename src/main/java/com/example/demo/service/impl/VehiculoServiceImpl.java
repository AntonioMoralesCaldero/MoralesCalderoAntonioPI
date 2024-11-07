//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Vehiculo;
import com.example.demo.model.VehiculoModel;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.CompraService;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	private final VehiculoRepository vehiculoRepository;
    private final CompraService compraService;

    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, CompraService compraService) {
        this.vehiculoRepository = vehiculoRepository;
        this.compraService = compraService;
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
    
    @Override
    public VehiculoModel saveVehiculo(VehiculoModel vehiculoModel) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setModelo(vehiculoModel.getModelo());
        vehiculo.setColor(vehiculoModel.getColor());
        vehiculo.setPrecio(vehiculoModel.getPrecio());
        vehiculo.setPotencia(vehiculoModel.getPotencia());
        vehiculo.setImagen(vehiculoModel.getImagen());

        Vehiculo savedVehiculo = vehiculoRepository.save(vehiculo);
        return new VehiculoModel(
            savedVehiculo.getId(),
            savedVehiculo.getModelo(),
            savedVehiculo.getColor(),
            savedVehiculo.getPrecio(),
            savedVehiculo.getPotencia(),
            savedVehiculo.getImagen()
        );
    }
    
    @Override
    public List<VehiculoModel> getCochesByUsuarioId(int usuarioId) {
        List<Compra> compras = compraService.obtenerComprasPorUsuario(usuarioId);
        List<VehiculoModel> vehiculoModels = new ArrayList<>();
        
        for (Compra compra : compras) {
            Vehiculo vehiculo = compra.getVehiculo();
            vehiculoModels.add(new VehiculoModel(
                vehiculo.getId(),
                vehiculo.getModelo(),
                vehiculo.getColor(),
                vehiculo.getPrecio(),
                vehiculo.getPotencia(),
                vehiculo.getImagen()
            ));
        }
        return vehiculoModels;
    }
}
