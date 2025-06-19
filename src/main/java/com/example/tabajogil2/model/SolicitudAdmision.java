package com.example.tabajogil2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Table("solicitudadmision")
public class SolicitudAdmision {

    @Id
    @Column("idSolicitud")
    private Long idSolicitud;

    @Column("idEstudiante")
    private Long idEstudiante;

    @Column("fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column("programaPostulado")
    private String programaPostulado;

    @Column("comentarios")
    private String comentarios;

    @Column("estado")
    private String estado;
}