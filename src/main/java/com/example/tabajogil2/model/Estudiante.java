package com.example.tabajogil2.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("estudiante")
public class Estudiante {

    @Id
    @Column("idEstudiante")
    private Long idEstudiante;
    private String nombre;
    private String correo;

    @Column("tipoEstudiante")
    private String tipoEstudiante; // PREGRADO, POSTGRADO, INTERCAMBIO
    private String edad;
}