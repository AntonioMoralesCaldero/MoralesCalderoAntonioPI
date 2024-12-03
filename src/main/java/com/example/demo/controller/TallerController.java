// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.model.CitaModel;
import com.example.demo.model.UsuarioModel;
import com.example.demo.model.ValoracionModel;
import com.example.demo.model.VehiculoModel;
import com.example.demo.service.CitaService;
import com.example.demo.service.ValoracionService;
import com.example.demo.service.VehiculoService;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taller")
public class TallerController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValoracionService valoracionService;
    
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/citas")
    public String verCitas(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        List<CitaModel> citas = citaService.obtenerCitasPorUsuario(usuario.getId());
        model.addAttribute("citas", citas);
        return "citas";
    }

    @GetMapping("/citas/nueva")
    public String formularioCita(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        List<VehiculoModel> vehiculos = vehiculoService.getCochesByUsuarioId(usuario.getId());
        model.addAttribute("vehiculos", vehiculos);

        model.addAttribute("citaModel", new CitaModel());
        return "crear-cita";
    }

    @PostMapping("/citas/crear")
    public String crearCita(@ModelAttribute CitaModel citaModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuario.getId());
        usuarioModel.setNombre(usuario.getNombre());
        usuarioModel.setApellidos(usuario.getApellidos());
        usuarioModel.setUsername(usuario.getUsername());

        citaModel.setUsuario(usuarioModel);

        if (citaModel.getVehiculoOcasionId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un vehículo.");
        }

        citaService.crearCita(citaModel);
        return "redirect:/taller/citas";
    }


    @GetMapping("/citas/{id}/valorar")
    public String formularioValorar(@PathVariable int id, Model model) {
        CitaModel cita = citaService.obtenerCitaPorId(id);

        if (cita == null || !cita.getEstado().equalsIgnoreCase("terminado") || cita.isValorada()) {
            return "redirect:/taller/citas";
        }

        model.addAttribute("cita", cita);
        model.addAttribute("valoracion", new ValoracionModel());
        return "valorar-cita";
    }


    @PostMapping("/citas/{id}/valorar")
    public String guardarValoracion(
            @PathVariable int id,
            @ModelAttribute ValoracionModel valoracionModel) {

        CitaModel cita = citaService.obtenerCitaPorId(id);

        if (cita != null && cita.getEstado().equalsIgnoreCase("terminado") && !cita.isValorada()) {
            valoracionModel.setCitaId(cita.getId());
            valoracionModel.setUsuarioId(cita.getUsuario().getId());
            
            valoracionService.guardarValoracion(valoracionModel);

            cita.setValorada(true);
            citaService.actualizarCita(cita);
        }

        return "redirect:/taller/citas";
    }


    @GetMapping("/citas/no-valoradas")
    public String verCitasNoValoradas(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        List<CitaModel> citasNoValoradas = citaService.obtenerCitasNoValoradasPorUsuario(usuario.getId());
        model.addAttribute("citasNoValoradas", citasNoValoradas);

        return "citas-no-valoradas";
    }
}
