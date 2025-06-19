package com.example.tabajogil2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformacionAcademicaCursoDao {

    private final JdbcTemplate jdbcTemplate;

    public InformacionAcademicaCursoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean yaExisteRelacion(Long idCurso, Long idInformacion) {
        String sql = "SELECT COUNT(*) FROM informacionacademicacurso WHERE idCurso = ? AND idInformacion = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idCurso, idInformacion);
        return count != null && count > 0;
    }

    public void guardarRelacion(Long idCurso, Long idInformacion) {
        if (!yaExisteRelacion(idCurso, idInformacion)) {
            String sql = "INSERT INTO informacionacademicacurso (idCurso, idInformacion) VALUES (?, ?)";
            jdbcTemplate.update(sql, idCurso, idInformacion);
        }
    }

    public void eliminarRelacionesDeCurso(Long idCurso) {
        String sql = "DELETE FROM informacionacademicacurso WHERE idCurso = ?";
        jdbcTemplate.update(sql, idCurso);
    }
}