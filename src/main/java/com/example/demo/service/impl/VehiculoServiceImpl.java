// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Vehiculo;
import com.example.demo.model.VehiculoModel;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.CompraRepository;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final CompraRepository compraRepository;
    private final CitaRepository citaRepository;

    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, CompraRepository compraRepository, CitaRepository citaRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.compraRepository = compraRepository;
        this.citaRepository = citaRepository;
    }

    @Override
    public List<VehiculoModel> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        List<VehiculoModel> vehiculoModels = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            vehiculoModels.add(convertirEntidadAModelo(vehiculo));
        }
        return vehiculoModels;
    }

    @Override
    public VehiculoModel getVehiculoById(int id) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id);
        return optionalVehiculo.map(this::convertirEntidadAModelo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con el ID: " + id));
    }

    @Override
    public VehiculoModel saveVehiculo(VehiculoModel vehiculoModel) {
        Vehiculo vehiculo = convertirModeloAEntidad(vehiculoModel);
        Vehiculo savedVehiculo = vehiculoRepository.save(vehiculo);
        return convertirEntidadAModelo(savedVehiculo);
    }

    @Override
    public List<VehiculoModel> getCochesByUsuarioId(int usuarioId) {
        List<Compra> compras = compraRepository.findByUsuarioId(usuarioId);
        List<VehiculoModel> vehiculoModels = new ArrayList<>();
        for (Compra compra : compras) {
            vehiculoModels.add(convertirEntidadAModelo(compra.getVehiculo()));
        }
        return vehiculoModels;
    }

    @Override
    public VehiculoModel convertirEntidadAModelo(Vehiculo vehiculo) {
        return new VehiculoModel(
            vehiculo.getId(),
            vehiculo.getModelo(),
            vehiculo.getColor(),
            vehiculo.getPrecio(),
            vehiculo.getPotencia(),
            vehiculo.getImagen()
        );
    }

    @Override
    public Vehiculo convertirModeloAEntidad(VehiculoModel vehiculoModel) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(vehiculoModel.getId());
        vehiculo.setModelo(vehiculoModel.getModelo());
        vehiculo.setColor(vehiculoModel.getColor());
        vehiculo.setPrecio(vehiculoModel.getPrecio());
        vehiculo.setPotencia(vehiculoModel.getPotencia());
        vehiculo.setImagen(vehiculoModel.getImagen());
        return vehiculo;
    }

    @Override
    public Vehiculo findVehiculoById(int id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public void agregarVehiculoDesdeOferta(Oferta oferta) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setModelo(oferta.getModelo());
        vehiculo.setColor(oferta.getColor());
        vehiculo.setPrecio(oferta.getPrecio());
        vehiculo.setPotencia(oferta.getPotencia());
        vehiculo.setImagen(oferta.getImagen());

        vehiculoRepository.save(vehiculo);
    }
    
    @Override
    public List<VehiculoModel> obtenerVehiculosOcasionales() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        List<VehiculoModel> vehiculosOcasionales = vehiculos.stream()
                .filter(vehiculo -> citaRepository.findByVehiculoOcasion(vehiculo).isEmpty())
                .map(this::convertirEntidadAModelo)
                .collect(Collectors.toList());

        return vehiculosOcasionales;
    }

}
