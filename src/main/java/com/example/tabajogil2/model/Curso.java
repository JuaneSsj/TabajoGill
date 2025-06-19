package com.example.tabajogil2.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("curso")
public class Curso {

    @Id
    @Column("idCurso")
    private Long idCurso;

    @Column("nombreCurso")
    private String nombreCurso;

    @Column("codigoCurso")
    private String codigoCurso;

    @Column("creditos")
    private Integer creditos;

    @Column("tipoCurso")
    private String tipoCurso; // O enum si prefieres


}