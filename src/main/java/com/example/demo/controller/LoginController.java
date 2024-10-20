//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.UsuarioModel;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(@Qualifier("usuarioService") UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "register";
    }

    @PostMapping("/auth/register")
    public String registerForm(@ModelAttribute("usuarioModel") UsuarioModel usuarioModel, RedirectAttributes flash) {
        try {
            usuarioService.registrar(usuarioModel);
            flash.addFlashAttribute("success", "Usuario registrado con éxito!!");
            return "redirect:/auth/login";
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            return "redirect:/auth/register";
        }
    }


    @GetMapping("/auth/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuario o contraseña inválido");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Has cerrado sesión correctamente");
        }
        return "login";
    }
    
    @PostMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, RedirectAttributes flash) {
        UsuarioModel usuarioModel = usuarioService.login(username, password);
        if (usuarioModel != null) {
            session.setAttribute("usuarioId", usuarioModel.getId());
            return "redirect:/compras/medicamentos";
        } else {
            flash.addFlashAttribute("errorMessage", "Usuario o contraseña inválido");
            return "redirect:/auth/login";
        }
    }


}