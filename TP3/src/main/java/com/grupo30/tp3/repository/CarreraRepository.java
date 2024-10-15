package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CarreraRepository")
public interface CarreraRepository extends RepoBase<Carrera, Long> {

    @Query("SELECT c, COUNT( e.estudiante) AS inscriptos FROM Carrera c JOIN Estudia e ON c.id = e.carrera.id GROUP BY c.id, c.carrera, c.duracion ORDER BY inscriptos DESC")
    List<Object[]> getCarrerasOrdenadas();

    @Query("SELECT c.carrera, es.inscripcion, COUNT(DISTINCT es.estudiante.documento), SUM(CASE WHEN es.graduacion > 0 THEN 1 ELSE 0 END) FROM Carrera c JOIN c.estudiantes es GROUP BY c.carrera, es.inscripcion ORDER BY c.carrera ASC, es.inscripcion ASC")
    List<Object[]> getReporteCarreras();
}
