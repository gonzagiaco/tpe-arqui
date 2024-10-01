package com.grupo30.dao;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudiante;
import com.grupo30.utils.CsvRecords;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

public class CarreraDAO implements InterfaceDAO<Carrera>{

    private EntityManager em;

    public CarreraDAO(EntityManager em){

        this.em = em;
    }

    @Override
    public void insertAll(String csvURL) {
        try{
            String[] HEADERS = {"id_carrera", "carrera", "duracion"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, csvURL);

            for(CSVRecord row : records){
                int id_carrera = Integer.parseInt(row.get("id_carrera"));
                String carrera = row.get("carrera");
                int duracion = Integer.parseInt(row.get("duracion"));
                Carrera c = new Carrera(id_carrera, carrera, duracion);
                insert(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
