//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
