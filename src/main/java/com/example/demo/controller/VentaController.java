// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.model.OfertaModel;
import com.example.demo.model.UsuarioModel;
import com.example.demo.model.VehiculoModel;
import com.example.demo.service.OfertaService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
public class VentaController {

    private final VehiculoService vehiculoService;
    private final UsuarioService usuarioService;
    private final OfertaService ofertaService;

    @Autowired
    public VentaController(VehiculoService vehiculoService, UsuarioService usuarioService, OfertaService ofertaService) {
        this.vehiculoService = vehiculoService;
        this.usuarioService = usuarioService;
        this.ofertaService = ofertaService;
    }

    @GetMapping("/mis-coches")
    public String mostrarCochesDelUsuario(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/auth/login";
        }

        Usuario usuario = usuarioService.findByUsername(principal.getName());
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        List<VehiculoModel> cochesComprados = vehiculoService.getCochesByUsuarioId(usuario.getId());
        model.addAttribute("cochesComprados", cochesComprados);
        return "venta";
    }

    @GetMapping("/vender-tu-coche/agregar")
    public String mostrarFormularioVenta(Model model) {
        model.addAttribute("vehiculo", new VehiculoModel());
        return "formularioCoche";
    }

    @PostMapping("/vender-tu-coche/agregar")
    public String agregarVehiculo(@ModelAttribute OfertaModel ofertaModel,
                                   @RequestParam("imagenFile") MultipartFile imagenFile,
                                   Principal principal) {
        if (principal == null) {
            return "redirect:/auth/login";
        }

        Usuario usuario = usuarioService.findByUsername(principal.getName());
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        String nombreImagen = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
        try {
            Path rutaArchivo = Paths.get("src/main/resources/static/images/" + nombreImagen);
            Files.write(rutaArchivo, imagenFile.getBytes());
            ofertaModel.setImagen(nombreImagen);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }

        ofertaModel.setEstado("PENDIENTE");
        ofertaService.guardarOferta(ofertaModel);

        return "redirect:/mis-coches";
    }



    @GetMapping("/venta")
    public String redirigirAMisCoches() {
        return "redirect:/mis-coches";
    }
}
