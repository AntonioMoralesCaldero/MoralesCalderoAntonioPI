// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.UsuarioModel;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final UsuarioService usuarioService;
    private final Map<String, Integer> activeTokens = new HashMap<>();

    @Autowired
    public AuthApiController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioModel usuarioModel) {
        try {
            usuarioService.registrar(usuarioModel);
            return ResponseEntity.ok("Usuario registrado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam("username") String username,
                                                     @RequestParam("password") String password) {
        UsuarioModel usuarioModel = usuarioService.login(username, password);
        if (usuarioModel != null) {
            String token = UUID.randomUUID().toString();
            activeTokens.put(token, usuarioModel.getId());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Inicio de sesión exitoso.");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Usuario o contraseña inválidos."));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (activeTokens.containsKey(token)) {
            activeTokens.remove(token);
            return ResponseEntity.ok("Sesión cerrada correctamente.");
        } else {
            return ResponseEntity.status(401).body("Token inválido o expirado.");
        }
    }

    public Integer validateToken(String token) {
        System.out.println("Validando token: " + token);
        return activeTokens.get(token);
    }

}
