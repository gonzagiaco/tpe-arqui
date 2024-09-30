package com.grupo30.repository;

import com.grupo30.dao.EstudianteDAO;
import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;

public class EstudianteRepository {

    private EstudianteDAO estudianteDAO;

    public EstudianteRepository(EntityManager em) {

        this.estudianteDAO = new EstudianteDAO(em);
    }

    //a- Recuperar todos los estudiantes
    public void darDeAltaEstudiante(Estudiante estudiante) {

        this.estudianteDAO.insert(estudiante);

    }
    //d- Recuperar estudiante por nro libreta
    public Estudiante recuperarEstudianteNroLibreta(int nro){
        return this.estudianteDAO.find(Estudiante.class, nro_libreta)
    }

    //c- recuperar todos los estudiantes
    public List<Estudiante> recuperarEstudiantes(){
        return this.estudianteDAO.selectAll();
    }

    //e- Recuperar estudiantes por genero
    public List<Estudiante> recuperarEstudianteGenero(String genero){
        return estudianteDAO.selectByGenero(genero);
    }
}
