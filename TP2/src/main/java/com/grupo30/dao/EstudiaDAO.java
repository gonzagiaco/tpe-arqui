package com.grupo30.dao;

import com.grupo30.entidades.Estudia;
import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;

public class EstudiaDAO implements InterfaceDAO<Estudia>{
    private EntityManager em;

    public EstudiaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Estudia entidad) {
        em.persist(entidad);
    }

    @Override
    public void update(Estudia entidad) {

        Estudiante e = em.find(Estudiante.class, entidad.getId());

        if(e != null){
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
    public Estudia select(int id) {
        Estudia e = em.find(Estudia.class, id);

        if(e != null){
            return e;
        }

        return null;
    }
}
