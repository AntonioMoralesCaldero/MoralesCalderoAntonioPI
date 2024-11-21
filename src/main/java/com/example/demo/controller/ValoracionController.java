package com.example.demo.controller;

import com.example.demo.model.ValoracionModel;
import com.example.demo.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/taller/valoracion")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping("/agregar")
    public String agregarValoracion(@ModelAttribute ValoracionModel valoracionModel) {
        valoracionService.agregarValoracion(valoracionModel);
        return "redirect:/taller/citas";
    }
}
