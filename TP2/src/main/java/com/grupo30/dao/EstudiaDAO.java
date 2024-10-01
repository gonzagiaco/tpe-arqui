package com.grupo30.dao;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudia;
import com.grupo30.entidades.Estudiante;
import com.grupo30.utils.CsvRecords;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.Query;


import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

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
    public void insertAll(String csvURL) {
        try{
            String[] HEADERS = {"id", "id_estudiante", "id_carrera", "inscripcion", "graduacion", "antiguedad"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, csvURL);

            for(CSVRecord row : records){
                int id = Integer.parseInt(row.get("id"));
                int id_estudiante = Integer.parseInt(row.get("id_estudiante"));
                Estudiante e = em.find(Estudiante.class, id_estudiante);
                int id_carrera = Integer.parseInt(row.get("id_carrera"));
                Carrera c = em.find(Carrera.class, id_carrera);
                int inscripcion = Integer.parseInt(row.get("inscripcion"));
                int graduacion = Integer.parseInt(row.get("graduacion"));
                int antiguedad = Integer.parseInt(row.get("antiguedad"));
                Estudia es = new Estudia(id, e, c, inscripcion, graduacion, antiguedad);
                insert(es);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        String jpql = "SELECT c.carrera, es.inscripcion, " +
                "COUNT(DISTINCT es.estudiante.id) AS inscriptos, " +
                "SUM(CASE WHEN es.graduado > 0 THEN 1 ELSE 0 END) AS egresados " +
                "FROM Carrera c " +
                "JOIN c.estudiantes es " +
                "GROUP BY c.carrera, es.inscripcion " +
                "ORDER BY c.carrera ASC, es.inscripcion ASC";

        Query query = em.createQuery(jpql);

        return query.getResultList();
    }

}
