// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Vehiculo;
import com.example.demo.model.VehiculoModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.CompraService;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompraController {

    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CompraService compraService;

    @Autowired
    public CompraController(VehiculoRepository vehiculoRepository, UsuarioRepository usuarioRepository, CompraService compraService) {
    	
        this.vehiculoRepository = vehiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.compraService = compraService;
    }

    @PostMapping("/compra")
    public String comprarVehiculo(@RequestParam("vehiculoId") int vehiculoId, 
                                  @AuthenticationPrincipal CustomUserDetails usuarioDetails, 
                                  Model model) {
        if (usuarioDetails == null) {
            model.addAttribute("mensajeError", "Usuario no autenticado.");
            return "errorPage";
        }

        Usuario usuario = usuarioRepository.findById(usuarioDetails.getId())
                                           .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                                              .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        if (vehiculo.isVendido()) {
            model.addAttribute("mensajeError", "Este coche ya fue vendido.");
            return "errorPage";
        }

        vehiculo.setVendido(true);
        vehiculoRepository.save(vehiculo);

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setVehiculo(vehiculo);
        compraService.registrarCompra(compra);

        model.addAttribute("mensaje", "¡Enhorabuena, has comprado este coche!");

        return "confirmacionCompra";
    }




}
