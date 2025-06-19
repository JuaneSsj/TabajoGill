package com.example.tabajogil2.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("informacionacademica")
public class InformacionAcademica {
    @Id
    @Column("idInformacion")
    private Long idInformacion;
    private Double promedio;

    @Column("universidadOrigen")
    private String universidadOrigen;
    @Column("fechaIngreso")
    private String fechaIngreso;
    private String nivel; // LICENCIATURA, MAESTRIA, DOCTORADO
    @Column("idEstudiante")
    private Long idEstudiante;
}
