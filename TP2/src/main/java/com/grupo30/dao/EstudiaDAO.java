package com.grupo30.dao;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudia;
import com.grupo30.entidades.Estudiante;
import javax.persistence.Query;


import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

public class EstudiaDAO implements InterfaceDAO<Estudia>{
    private EntityManager em;

    public EstudiaDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Estudia entidad) {
        em.persist(entidad);

        entidad.getEstudiante().getCarreras().add(entidad);
        entidad.getCarrera().getEstudiantes().add(entidad);
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

    public List<Object[]> getReportes(){

        String jpql = "SELECT c.nombre, e.antiguedad, COUNT(e) AS inscriptos, " +
                "SUM(CASE WHEN e.graduado = TRUE THEN 1 ELSE 0 END) AS egresados " +
                "FROM Carrera c " +
                "JOIN c.estudiantes e " +
                "GROUP BY c.nombre, e.antiguedad " +
                "ORDER BY c.nombre ASC, e.antiguedad ASC";

        Query query = em.createQuery(jpql);

        return query.getResultList();
    }

}
