package com.example.tabajogil2;

import com.example.tabajogil2.model.Estudiante;
import com.example.tabajogil2.model.InformacionAcademica;
import lombok.Data;


@Data
public class EstudianteForm {
    private Estudiante estudiante;
    private InformacionAcademica informacionAcademica;
}