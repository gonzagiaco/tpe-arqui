package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Estudiante;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Usar query manual
    @Query("SELECT e FROM Estudiante e WHERE e.nro_libreta = :nroLibreta")
    Optional<Estudiante> findByNroLibreta(@Param("nroLibreta") int nroLibreta);

}
