package com.grupo30.dao;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

import com.grupo30.utils.CsvRecords;
import org.apache.commons.csv.CSVRecord;


public class EstudianteDAO implements InterfaceDAO<Estudiante>{

    private EntityManager em;

    public EstudianteDAO(EntityManager em){

        this.em = em;
    }

    @Override
    public void insertAll(String csvURL) {
        try{
            String[] HEADERS = {"DNI", "nombre", "apellido", "edad", "genero", "ciudad", "LU"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, csvURL);

            for(CSVRecord row : records){
                int DNI = Integer.parseInt(row.get("DNI"));
                String nombre = row.get("nombre");
                String apellido = row.get("apellido");
                int edad = Integer.parseInt(row.get("edad"));
                String genero = row.get("genero");
                String ciudad = row.get("ciudad");
                int lu = Integer.parseInt(row.get("LU"));
                Estudiante e = new Estudiante(DNI, nombre, apellido, edad, genero, ciudad, lu);
                insert(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public Estudiante select(int dni) {
        Estudiante e = em.find(Estudiante.class, dni);
        if(e != null){
            return e;
        }
        return null;
    }


    public List<Estudiante> selectAll(String orden) {

        String campoOrden;
        switch(orden.toLowerCase()) {
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
        String jpql = "SELECT e FROM Estudiante e JOIN e.carreras es WHERE es.carrera.carrera = :carrera AND LOWER(e.ciudad_residencia) = :ciudad";
        Query query = em.createQuery(jpql);
        query.setParameter("carrera", c.getCarrera());
        query.setParameter("ciudad", ciudadLower);

        return query.getResultList();
     }

     public Estudiante selectByNroLibreta(int nro){
        String jpql = "SELECT e FROM Estudiante e WHERE e.nro_libreta = :nro";
        Query query = em.createQuery(jpql);
        query.setParameter("nro", nro);
        return (Estudiante) query.getSingleResult();
     }
}
