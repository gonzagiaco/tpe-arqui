package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Estudiante;
import org.springframework.stereotype.Repository;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends RepoBase<Estudiante, Long> {

}
