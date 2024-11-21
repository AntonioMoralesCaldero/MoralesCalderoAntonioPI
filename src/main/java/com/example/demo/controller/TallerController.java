package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.model.CitaModel;
import com.example.demo.model.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.CitaService;
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
        model.addAttribute("citaModel", new CitaModel());
        return "crear-cita";
    }

    @PostMapping("/citas/crear")
    public String crearCita(@ModelAttribute CitaModel citaModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        // Crear un UsuarioModel a partir del Usuario
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuario.getId());
        usuarioModel.setNombre(usuario.getNombre());
        usuarioModel.setApellidos(usuario.getApellidos());
        usuarioModel.setUsername(usuario.getUsername());
        // Agrega más propiedades si es necesario

        // Asignar el usuarioModel a la cita
        citaModel.setUsuario(usuarioModel);

        // Crear la cita
        citaService.crearCita(citaModel);

        return "redirect:/taller/citas";
    }

}
