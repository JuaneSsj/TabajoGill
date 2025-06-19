package com.example.tabajogil2.repository;


import com.example.tabajogil2.model.SolicitudAdmision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudAdmisionRepository extends CrudRepository<SolicitudAdmision, Long> {
    List<SolicitudAdmision> findByIdEstudiante(Long idEstudiante);
}
