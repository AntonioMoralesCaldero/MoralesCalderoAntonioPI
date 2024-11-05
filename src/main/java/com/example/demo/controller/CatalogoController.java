//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.VehiculoModel;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogoController {

    private final VehiculoService vehiculoService;

    @Autowired
    public CatalogoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        model.addAttribute("vehiculos", vehiculoService.getAllVehiculos());
        return "catalogo";
    }
    
    @GetMapping("/vehiculo/detalle/{id}")
    public String mostrarDetalle(@PathVariable int id, Model model) {
        VehiculoModel vehiculo = vehiculoService.getVehiculoById(id);
        model.addAttribute("vehiculo", vehiculo);
        return "detalle";
    }

}
