// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Oferta;
import com.example.demo.service.OfertaService;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/ofertas")
    public String verOfertas(Model model) {
        List<Oferta> ofertasPendientes = ofertaService.obtenerOfertasPendientes();
        model.addAttribute("ofertas", ofertasPendientes);
        return "adminDashboard";
    }

    @PostMapping("/ofertas/{id}/aceptar")
    public String aceptarOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.obtenerOfertaPorId(id);
        if (oferta != null) {
            oferta.setEstado("aceptada");
            ofertaService.actualizarOferta(oferta);

            vehiculoService.agregarVehiculoDesdeOferta(oferta);
        }
        return "redirect:/admin/ofertas";
    }

    @PostMapping("/ofertas/{id}/rechazar")
    public String rechazarOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.obtenerOfertaPorId(id);
        if (oferta != null) {
            oferta.setEstado("rechazada");
            ofertaService.actualizarOferta(oferta);
        }
        return "redirect:/admin/ofertas";
    }
}
