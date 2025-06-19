package com.example.tabajogil2.controller;

import com.example.tabajogil2.EstudianteForm;
import com.example.tabajogil2.model.Estudiante;
import com.example.tabajogil2.model.InformacionAcademica;
import com.example.tabajogil2.repository.EstudianteRepository;
import com.example.tabajogil2.repository.InformacionAcademicaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudianteRepo;
    private final InformacionAcademicaRepository infoAcademicaRepo;

    public EstudianteController(EstudianteRepository estudianteRepo,
                                InformacionAcademicaRepository infoAcademicaRepo) {
        this.estudianteRepo = estudianteRepo;
        this.infoAcademicaRepo = infoAcademicaRepo;
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudianteForm", new EstudianteForm());
        return "formulario"; // formulario.html con campos del estudiante y académicos
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstudianteForm estudianteForm) {
        Estudiante estudiante = estudianteForm.getEstudiante();

        // Guardar estudiante y obtener el objeto guardado
        Estudiante estudianteGuardado = estudianteRepo.save(estudiante);
        Long estudianteId = estudianteGuardado.getIdEstudiante();

        // Asociar y guardar la información académica
        InformacionAcademica info = estudianteForm.getInformacionAcademica();
        info.setIdEstudiante(estudianteId);
        infoAcademicaRepo.save(info);

        return "redirect:/estudiantes/lista";
    }

    @GetMapping("/lista")
    public String listarTodos(Model model) {
        Iterable<Estudiante> iterable = estudianteRepo.findAll();
        List<Estudiante> estudiantes = new ArrayList<>();
        iterable.forEach(estudiantes::add);

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Estudiante e : estudiantes) {
            InformacionAcademica info = infoAcademicaRepo
                    .findByIdEstudiante(e.getIdEstudiante())
                    .orElse(null);

            Map<String, Object> fila = new HashMap<>();
            fila.put("estudiante", e);
            fila.put("info", info);
            resultado.add(fila);
        }

        model.addAttribute("datos", resultado);
        return "lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        infoAcademicaRepo.deleteByIdEstudiante(id);
        estudianteRepo.deleteById(id);
        return "redirect:/estudiantes/lista";
    }
}