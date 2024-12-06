// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Oferta;
import com.example.demo.model.CitaModel;
import com.example.demo.model.ValoracionModel;
import com.example.demo.model.VehiculoModel;
import com.example.demo.service.CitaService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.ValoracionService;
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
    
    @Autowired
    private ValoracionService valoracionService;

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


    @PostMapping("/citas/{id}/actualizar")
    public String actualizarCita(
            @PathVariable int id,
            @RequestParam String diagnostico,
            @RequestParam String estado,
            @RequestParam(required = false) String fechaReparacionFinalizada,
            @RequestParam(required = false) String fechaCita,
            @RequestParam(required = false) Integer vehiculoOcasionId) {

        CitaModel cita = citaService.obtenerCitaPorId(id);

        if (cita != null) {
            cita.setDiagnostico(diagnostico);
            
            if ("pendiente".equals(estado) || "Terminado".equals(estado)) {
                cita.setEstado(estado);
            }

            if (fechaCita != null && !fechaCita.isEmpty()) {
                cita.setFechaCita(LocalDateTime.parse(fechaCita));
            }

            if (fechaReparacionFinalizada != null && !fechaReparacionFinalizada.isEmpty()) {
                cita.setFechaReparacionFinalizada(LocalDateTime.parse(fechaReparacionFinalizada));
            }

            if (vehiculoOcasionId != null) {
                cita.setVehiculoOcasionId(vehiculoOcasionId);
            }

            citaService.actualizarCita(cita);
        }

        return "redirect:/admin/citas";
    }
    
    @PostMapping("/citas/{id}/cancelar")
    public String cancelarCita(@PathVariable int id) {
        try {
            citaService.cancelarCita(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/citas";
    }

    
    @GetMapping("/valoraciones")
    public String listarValoraciones(Model model) {
        List<ValoracionModel> valoraciones = valoracionService.obtenerTodasValoraciones();
        model.addAttribute("valoraciones", valoraciones);
        return "admin-valoraciones";
    }
    
    @PostMapping("/valoraciones/{id}/editar")
    public String editarComentarioValoracion(
            @PathVariable int id,
            @RequestParam("comentario") String comentario) {

        ValoracionModel valoracion = valoracionService.obtenerValoracionPorId(id);

        if (valoracion != null) {
            valoracion.setComentario(comentario);
            valoracionService.actualizarValoracion(valoracion);
        }

        return "redirect:/admin/valoraciones";
    }



}
