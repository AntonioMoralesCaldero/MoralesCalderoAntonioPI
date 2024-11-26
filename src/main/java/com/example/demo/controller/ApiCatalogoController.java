//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.VehiculoModel;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiCatalogoController {

    private final VehiculoService vehiculoService;

    @Autowired
    public ApiCatalogoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/api/catalogo")
    public List<VehiculoModel> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }

    @GetMapping("/api/vehiculo/{id}")
    public VehiculoModel getVehiculoById(@PathVariable int id) {
        return vehiculoService.getVehiculoById(id);
    }
}
