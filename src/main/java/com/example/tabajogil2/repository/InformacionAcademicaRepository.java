package com.example.tabajogil2.repository;

import com.example.tabajogil2.model.InformacionAcademica;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InformacionAcademicaRepository extends CrudRepository<InformacionAcademica, Long> {

    // ✅ Este método buscará por el ID del estudiante
    Optional<InformacionAcademica> findByIdEstudiante(Long idEstudiante);

    // ✅ Este método borrará registros vinculados a un estudiante
    void deleteByIdEstudiante(Long idEstudiante);

    @Query("SELECT ia.* FROM informacionacademica ia " +
            "JOIN informacionacademicacurso iac ON ia.idInformacion = iac.idInformacion " +
            "WHERE iac.idCurso = :cursoId")
    List<InformacionAcademica> findInformacionesByCursoId(@Param("cursoId") Long cursoId);


}
