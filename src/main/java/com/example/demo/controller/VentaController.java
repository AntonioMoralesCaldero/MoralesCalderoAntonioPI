package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.model.VehiculoModel;
import com.example.demo.service.CompraService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class VentaController {

    private final VehiculoService vehiculoService;
    private final UsuarioService usuarioService;
    private final CompraService compraService;

    @Autowired
    public VentaController(VehiculoService vehiculoService, UsuarioService usuarioService, CompraService compraService) {
        this.vehiculoService = vehiculoService;
        this.usuarioService = usuarioService;
        this.compraService = compraService;
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
    public String agregarVehiculo(@ModelAttribute VehiculoModel vehiculoModel, Principal principal) {
        if (principal == null) {
            return "redirect:/auth/login";
        }

        Usuario usuario = usuarioService.findByUsername(principal.getName());
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        VehiculoModel vehiculoGuardado = vehiculoService.saveVehiculo(vehiculoModel);
        compraService.crearCompra(usuario.getId(), vehiculoGuardado.getId());
        return "redirect:/mis-coches";
    }


    @GetMapping("/venta")
    public String redirigirAMisCoches() {
        return "redirect:/mis-coches";
    }
}
