package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
}
