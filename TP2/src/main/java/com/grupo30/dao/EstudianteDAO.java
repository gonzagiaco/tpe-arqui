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
    public Estudiante select(int nro_libreta) {
        Estudiante e = em.find(Estudiante.class, nro_libreta);
        if(e != null){
            return e;
        }
        return null;
    }
    //Recuperar todos los estudiantes
    public List<Estudiante> selectAll(String orden){
        TypedQuery<Estudiante> query=em.createQuery("SELECT e FROM Estudiante e ORDER BY orden", Estudiante.class);
        return query.getResultList();
    }
    //Recupear estudiantes por genero
     public List<Estudiante> selectByGenero(String genero){
        TypedQuery<Estudiante> query=em.createQuery("SELECT e FROM Estudiante e WHERE e.genero=:genero");
        query.setParameter("genero", genero);
        return query.getResultList();
     }

}
