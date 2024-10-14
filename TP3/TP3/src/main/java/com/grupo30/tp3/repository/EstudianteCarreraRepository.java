package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Estudia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<Estudia, Long> {
    
}
