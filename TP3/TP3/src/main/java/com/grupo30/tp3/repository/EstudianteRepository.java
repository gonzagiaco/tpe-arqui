package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Estudiante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends RepoBase<Estudiante, Long> {


    @Override
    @Query("SELECT e FROM Estudiante e")
    List<Estudiante> findAll();

    // Usar query manual
    @Query("SELECT e FROM Estudiante e WHERE e.nro_libreta = :nroLibreta")
    Optional<Estudiante> findByNroLibreta(@Param("nroLibreta") int nroLibreta);

    @Query("SELECT e FROM Estudiante e WHERE e.genero= :genero")
    List<Estudiante> findByGenero(@Param("genero") String genero);

    @Query("SELECT e.estudiante FROM Estudia e JOIN e.carrera c JOIN e.estudiante s WHERE c.carrera = :carrera AND s.ciudad_residencia = :ciudad")
    List<Estudiante> findByCarreraFilterCiudad(@Param("carrera") String carrera,@Param("ciudad") String ciudad);


}
