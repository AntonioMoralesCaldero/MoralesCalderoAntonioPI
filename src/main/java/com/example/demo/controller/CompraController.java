// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Vehiculo;
import com.example.demo.model.VehiculoModel;
import com.example.demo.repository.UsuarioRepository;
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

    private final CompraService compraService;
    private final VehiculoService vehiculoService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    public CompraController(CompraService compraService, VehiculoService vehiculoService) {
        this.compraService = compraService;
        this.vehiculoService = vehiculoService;
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
        
        VehiculoModel vehiculoModel = vehiculoService.getVehiculoById(vehiculoId);
        
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(vehiculoModel.getId());
        vehiculo.setModelo(vehiculoModel.getModelo());
        vehiculo.setColor(vehiculoModel.getColor());
        vehiculo.setPrecio(vehiculoModel.getPrecio());
        vehiculo.setPotencia(vehiculoModel.getPotencia());
        vehiculo.setImagen(vehiculoModel.getImagen());

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setVehiculo(vehiculo);

        compraService.registrarCompra(compra);
        model.addAttribute("mensaje", "¡Enhorabuena, has comprado este coche!");

        return "confirmacionCompra";
    }


}
