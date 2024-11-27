// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.VehiculoModel;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class ApiCatalogoController {

    private final VehiculoService vehiculoService;
    private final AuthApiController authApiController;

    @Autowired
    public ApiCatalogoController(VehiculoService vehiculoService, AuthApiController authApiController) {
        this.vehiculoService = vehiculoService;
        this.authApiController = authApiController;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoModel>> getAllVehiculos(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (authApiController.validateToken(token) != null) {
            return ResponseEntity.ok(vehiculoService.getAllVehiculos());
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoModel> getVehiculoById(@RequestHeader("Authorization") String token,
                                                         @PathVariable int id) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (authApiController.validateToken(token) != null) {
            return ResponseEntity.ok(vehiculoService.getVehiculoById(id));
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
