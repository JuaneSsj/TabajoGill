package com.example.tabajogil2.repository;



import com.example.tabajogil2.model.Curso;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Query("SELECT c.* FROM curso c " +
            "JOIN informacion_academica_curso iac ON c.id_curso = iac.id_curso " +
            "WHERE iac.id_informacion = :idInformacion")
    List<Curso> findCursosByInformacionId(@Param("idInformacion") Long idInformacion);
}

