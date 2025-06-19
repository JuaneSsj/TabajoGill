package com.example.tabajogil2.controller;


import com.example.tabajogil2.model.Estudiante;
import com.example.tabajogil2.model.SolicitudAdmision;
import com.example.tabajogil2.repository.EstudianteRepository;
import com.example.tabajogil2.repository.SolicitudAdmisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudAdmisionController {

    private final EstudianteRepository estudianteRepo;
    private final SolicitudAdmisionRepository solicitudRepo;

    public SolicitudAdmisionController(EstudianteRepository estudianteRepo, SolicitudAdmisionRepository solicitudRepo) {
        this.estudianteRepo = estudianteRepo;
        this.solicitudRepo = solicitudRepo;
    }

    // 🟢 Ver solicitudes
    @GetMapping("/estudiante/{id}")
    public String verSolicitudes(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<SolicitudAdmision> solicitudes = solicitudRepo.findByIdEstudiante(id);

        SolicitudAdmision nueva = new SolicitudAdmision();
        nueva.setIdEstudiante(id); // 🔥 ¡IMPORTANTE para que el input hidden no sea null!

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("nuevaSolicitud", nueva);

        return "ver-solicitudes"; // Este es tu HTML
    }

    // 🟢 Guardar solicitud
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SolicitudAdmision nuevaSolicitud) {
        solicitudRepo.save(nuevaSolicitud);
        return "redirect:/solicitudes/estudiante/" + nuevaSolicitud.getIdEstudiante();
    }
}



