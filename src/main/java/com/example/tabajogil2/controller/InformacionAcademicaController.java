package com.example.tabajogil2.controller;



import com.example.tabajogil2.model.Curso;
import com.example.tabajogil2.model.InformacionAcademica;
import com.example.tabajogil2.repository.InformacionAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/informacion-academica")
public class InformacionAcademicaController {

    @Autowired
    private InformacionAcademicaRepository informacionAcademicaRepository;

    @GetMapping
    public String listarInformacionAcademica(Model model) {
        model.addAttribute("informaciones", informacionAcademicaRepository.findAll());
        return "informacion_academica/lista"; // Vista: templates/informacion_academica/lista.html
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("informacion", new InformacionAcademica());
        return "informacion_academica/formulario"; // Vista: templates/informacion_academica/formulario.html
    }

    @PostMapping("/guardar")
    public String guardarInformacion(@ModelAttribute InformacionAcademica informacion) {
        informacionAcademicaRepository.save(informacion);
        return "redirect:/informacion-academica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInformacion(@PathVariable Long id) {
        informacionAcademicaRepository.deleteById(id);
        return "redirect:/informacion-academica";
    }


}

