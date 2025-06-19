package com.example.tabajogil2.model;

import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("informacionacademicacurso")
public class InformacionAcademicaCurso {

    private Long idInformacion;
    private Long idCurso;
}