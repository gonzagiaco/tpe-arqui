package com.grupo30.dao;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CarreraDAO implements InterfaceDAO<Carrera>{

    private EntityManager em;

    public CarreraDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Carrera entidad) {
        em.persist(entidad);
    }

    @Override
    public void update(Carrera entidad) {

        Estudiante e = em.find(Estudiante.class, entidad.getId());

        if(e != null){
            em.merge(entidad);
        }

    }

    @Override
    public void delete(int id) {

        Carrera e = em.find(Carrera.class, id);

        if(e != null){
            em.remove(e);
        }

    }

    @Override
    public Carrera select(int id) {
        Carrera e = em.find(Carrera.class, id);

        if(e != null){
            return e;
        }

        return null;
    }

    public List<Carrera> selectCarrerasConEstudiantesOrdenadas() {
        String jpql = "SELECT c FROM Carrera c JOIN c.estudiantes e GROUP BY c ORDER BY COUNT(e) DESC";
        Query query = em.createQuery(jpql);

        return query.getResultList();
    }

}
