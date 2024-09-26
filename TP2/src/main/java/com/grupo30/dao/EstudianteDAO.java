package com.grupo30.dao;

import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;


public class EstudianteDAO implements InterfaceDAO<Estudiante>{

    private EntityManager em;

    public EstudianteDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Estudiante entidad) {
        em.persist(entidad);
    }

    @Override
    public void update(Estudiante entidad) {

        Estudiante e = em.find(Estudiante.class, entidad.getNro_libreta());

        if(e != null){
            e.setDocumento(entidad.getDocumento());
            e.setNombre(entidad.getNombre());
            e.setApellido(entidad.getApellido());
            e.setEdad(entidad.getEdad());
            e.setGenero(entidad.getGenero());
            e.setCiudad_residencia(entidad.getCiudad_residencia());
            em.merge(entidad);
        }

    }

    @Override
    public void delete(int id) {

        Estudiante e = em.find(Estudiante.class, id);

        if(e != null){
            em.remove(e);
        }

    }

    @Override
    public Estudiante select(int id) {
        Estudiante e = em.find(Estudiante.class, id);

        if(e != null){
            return e;
        }

        return null;
    }
}
