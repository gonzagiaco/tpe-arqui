package com.grupo30.tp3.repository;

import com.grupo30.tp3.model.Estudia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("EstudianteCarreraRepository")
public interface EstudianteCarreraRepository extends RepoBase<Estudia, Long> {

}
