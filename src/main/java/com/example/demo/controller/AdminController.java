// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Oferta;
import com.example.demo.model.CitaModel;
import com.example.demo.model.VehiculoModel;
import com.example.demo.service.CitaService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private CitaService citaService;

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
    
    @GetMapping("/citas")
    public String verCitas(Model model) {
        List<CitaModel> citas = citaService.obtenerTodasCitas();
        List<VehiculoModel> vehiculosOcasionales = vehiculoService.obtenerVehiculosOcasionales();

        model.addAttribute("citas", citas);
        model.addAttribute("vehiculosOcasionales", vehiculosOcasionales);

        return "adminCitas";
    }

    // Update an appointment
    @PostMapping("/citas/{id}/actualizar")
    public String actualizarCita(
            @PathVariable int id,
            @RequestParam String diagnostico,
            @RequestParam String estado,
            @RequestParam(required = false) String fechaReparacionFinalizada,
            @RequestParam(required = false) Integer vehiculoOcasionId) {

        // Fetch the CitaModel by ID
        CitaModel cita = citaService.obtenerCitaPorId(id);

        if (cita != null) {
            // Update the fields of CitaModel
            cita.setDiagnostico(diagnostico);
            cita.setEstado(estado);

            if (fechaReparacionFinalizada != null && !fechaReparacionFinalizada.isEmpty()) {
                cita.setFechaReparacionFinalizada(LocalDateTime.parse(fechaReparacionFinalizada));
            }

            if (vehiculoOcasionId != null) {
                cita.setVehiculoOcasionId(vehiculoOcasionId);
            }

            // Save the updated CitaModel
            citaService.actualizarCita(cita);
        }

        return "redirect:/admin/citas";
    }
    //pedir los impls y service y pedir adminCitas entero

}
