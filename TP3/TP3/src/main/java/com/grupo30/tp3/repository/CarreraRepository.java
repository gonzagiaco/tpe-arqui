package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CarreraRepository")
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}
