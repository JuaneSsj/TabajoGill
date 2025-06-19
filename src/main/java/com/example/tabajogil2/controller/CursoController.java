package com.example.tabajogil2.controller;

import com.example.tabajogil2.model.Curso;
import com.example.tabajogil2.model.InformacionAcademica;
import com.example.tabajogil2.model.InformacionAcademicaCurso;
import com.example.tabajogil2.repository.CursoRepository;
import com.example.tabajogil2.repository.InformacionAcademicaCursoDao;
import com.example.tabajogil2.repository.InformacionAcademicaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final InformacionAcademicaRepository infoRepository;
    private final InformacionAcademicaCursoDao infoCursoDao; // ✅ JDBC DAO usado en *todas* las relaciones

    public CursoController(CursoRepository cursoRepository,
                           InformacionAcademicaRepository infoRepository,
                           InformacionAcademicaCursoDao infoCursoDao) {
        this.cursoRepository = cursoRepository;
        this.infoRepository = infoRepository;
        this.infoCursoDao = infoCursoDao;
    }

    @GetMapping
    public String listarCursos(Model model) {
        List<Curso> cursos = (List<Curso>) cursoRepository.findAll();
        Map<Long, List<InformacionAcademica>> cursoInformacionesMap = new HashMap<>();

        for (Curso curso : cursos) {
            List<InformacionAcademica> informaciones = infoRepository
                    .findInformacionesByCursoId(curso.getIdCurso()); // método personalizado
            cursoInformacionesMap.put(curso.getIdCurso(), informaciones);
        }

        model.addAttribute("cursos", cursos);
        model.addAttribute("cursoInformacionesMap", cursoInformacionesMap);
        return "cursos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("informaciones", infoRepository.findAll());
        return "cursos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso,
                               @RequestParam(value = "informacionesSeleccionadas", required = false) Long[] infoIds) {

        Curso cursoGuardado = cursoRepository.save(curso);

        if (infoIds != null) {
            for (Long idInfo : infoIds) {
                infoCursoDao.guardarRelacion(cursoGuardado.getIdCurso(), idInfo);
            }
        }

        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        // Elimina relaciones antes de borrar el curso
        infoCursoDao.eliminarRelacionesDeCurso(id);
        cursoRepository.deleteById(id);
        return "redirect:/cursos";
    }

    @GetMapping("/asignar/{id}")
    public String mostrarFormularioAsignar(@PathVariable Long id, Model model) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        model.addAttribute("curso", curso);
        model.addAttribute("informaciones", infoRepository.findAll());
        return "cursos/asignar-informaciones";
    }

    @PostMapping("/asignar")
    public String guardarAsignaciones(@RequestParam("cursoId") Long cursoId,
                                      @RequestParam("informacionesSeleccionadas") List<Long> infoIds) {
        for (Long idInfo : infoIds) {
            infoCursoDao.guardarRelacion(cursoId, idInfo);
        }

        return "redirect:/cursos";
    }

    @GetMapping("/ver-informaciones/{id}")
    public String verInformacionesDeCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        List<InformacionAcademica> informaciones = infoRepository.findInformacionesByCursoId(id); // método custom

        model.addAttribute("curso", curso);
        model.addAttribute("informaciones", informaciones);

        return "cursos/relacion-informaciones"; // vista nueva para este propósito
    }
}