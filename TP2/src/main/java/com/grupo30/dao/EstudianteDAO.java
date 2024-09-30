package com.grupo30.dao;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


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


    public List<Estudiante> selectAll(String orden) {

        String campoOrden;
        switch (orden.toLowerCase()) {
            case "nombre":
                campoOrden = "e.nombre";
                break;
            case "apellido":
                campoOrden = "e.apellido";
                break;
            case "edad":
                campoOrden = "e.edad";
                break;
            default:
                campoOrden = "e.id";
        }

        String jpql = "SELECT e FROM Estudiante e ORDER BY " + campoOrden;
        Query query = em.createQuery(jpql);

        return query.getResultList();
    }

    //Recupear estudiantes por genero
     public List<Estudiante> selectByGenero(String genero){


        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query query = em.createQuery(jpql);
        query.setParameter("genero", genero);

        return query.getResultList();

     }

     public List<Estudiante> selectEstudiantesPorCarrera(Carrera c, String ciudad){

        String ciudadLower = ciudad.toLowerCase();
        String jpql = "SELECT e FROM Estudiante e JOIN e.carreras es WHERE es.carrera.nombre = :carrera AND LOWER(e.ciudad_residencia) = :ciudad";
        Query query = em.createQuery(jpql);
        query.setParameter("carrera", c.getNombre());
        query.setParameter("ciudad", ciudadLower);
        return query.getResultList();
     }
}
