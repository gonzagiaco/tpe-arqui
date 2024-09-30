package com.grupo30.repository;

import com.grupo30.dao.EstudianteDAO;
import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;

public class EstudianteRepository {

    private EstudianteDAO estudianteDAO;

    public EstudianteRepository(EntityManager em) {

        this.estudianteDAO = new EstudianteDAO(em);
    }

    public void darDeAltaEstudiante(Estudiante estudiante) {

        this.estudianteDAO.insert(estudiante);

    }
    public Estudiante recuperarEstudianteNroLibreta(int nro){
        return this.estudianteDAO.find(Estudiante.class, nro_libreta)
    }

}
