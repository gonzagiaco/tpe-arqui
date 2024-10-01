package com.grupo30.repository;

import com.grupo30.dao.EstudianteDAO;
import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudiante;
import com.grupo30.DTO.EstudianteDTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class EstudianteRepository {

    private EstudianteDAO estudianteDAO;

    public EstudianteRepository(EntityManager em) {

        this.estudianteDAO = new EstudianteDAO(em);
    }

    public void insertarDatos(String csvURL){
        this.estudianteDAO.insertAll(csvURL);
    }

    //a- Recuperar todos los estudiantes
    public void darDeAltaEstudiante(Estudiante estudiante) {

        this.estudianteDAO.insert(estudiante);

    }

    //d- Recuperar estudiante por nro libreta
    public EstudianteDTO recuperarEstudianteNroLibreta(int nro){
        Estudiante estudiante = this.estudianteDAO.selectByNroLibreta(nro);
        if(estudiante != null){
            return new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(), estudiante.getGenero(), estudiante.getCiudad_residencia());
        }

        return null;
    }

    //c- recuperar todos los estudiantes
    public List<EstudianteDTO> recuperarEstudiantes(String orden){
        List<Estudiante> estudiantes = this.estudianteDAO.selectAll(orden);
        List<EstudianteDTO> estudiantesDTO = new ArrayList<EstudianteDTO>();

        for(Estudiante e : estudiantes){
            EstudianteDTO eDTO = new EstudianteDTO(e.getDocumento(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad_residencia());
            estudiantesDTO.add(eDTO);
        }

        return estudiantesDTO;
    }

    //e- Recuperar estudiantes por genero
    public List<EstudianteDTO> recuperarEstudianteGenero(String genero){
        List<Estudiante> estudiantes = this.estudianteDAO.selectByGenero(genero);
        List<EstudianteDTO> estudiantesDTO = new ArrayList<EstudianteDTO>();
        for(Estudiante e : estudiantes){
            EstudianteDTO eDTO = new EstudianteDTO(e.getDocumento(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad_residencia());
            estudiantesDTO.add(eDTO);
        }
        return estudiantesDTO;
    }

    public List<EstudianteDTO> selectEstudiantesPorCarrera(Carrera c, String ciudad){
        List<Estudiante> estudiantes = this.estudianteDAO.selectEstudiantesPorCarrera(c, ciudad);
        List<EstudianteDTO> estudiantesDTO = new ArrayList<EstudianteDTO>();

        for(Estudiante e : estudiantes){
            EstudianteDTO eDTO = new EstudianteDTO(e.getDocumento(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad_residencia());
            estudiantesDTO.add(eDTO);
        }

        return estudiantesDTO;
    }
}
