package com.grupo30.repository;

import com.grupo30.dao.EstudianteDAO;
import com.grupo30.entidades.Estudiante;
import com.grupo30.DTO.EstudianteDTO;

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
    public EstudianteDTO recuperarEstudianteNroLibreta(int nro){
        Estudiante estudiante = this.estudianteDAO.select(nro);
        EstudianteDTO estDTO = new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(), estudiante.getGenero(), estudiante.getCiudad_residencia());

        return estDTO;
    }

    //c- recuperar todos los estudiantes
    public List<EstudianteDTO> recuperarEstudiantes(String orden){
        return this.estudianteDAO.selectAll();
    }

    //e- Recuperar estudiantes por genero
    public List<Estudiante> recuperarEstudianteGenero(String genero){
        return estudianteDAO.selectByGenero(genero);
    }
}
