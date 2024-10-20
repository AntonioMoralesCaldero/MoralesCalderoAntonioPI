//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String index() {
	    return "index";
	}
   
    @GetMapping("/perfil")
    public String userProfile(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "profile";
    }


}