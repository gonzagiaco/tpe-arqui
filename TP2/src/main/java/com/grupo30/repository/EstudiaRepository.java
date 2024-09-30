package com.grupo30.repository;

import com.grupo30.dao.EstudiaDAO;
import com.grupo30.entidades.Estudia;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EstudiaRepository {

    private EstudiaDAO estudiaDAO;

    public EstudiaRepository(EntityManager em){
        this.estudiaDAO = new EstudiaDAO(em);
    }

    public void matricularEstudiante(Estudia estudia){
        this.estudiaDAO.insert(estudia);
    }

    public List<String> obtenerReportes(){
        List<Object[]> reportes = this.estudiaDAO.getReportes();
        List<String> reporteLista = new ArrayList<String>();

        for(Object[] reporte : reportes){
            reporteLista.add(String.valueOf(reporte[0]));
            reporteLista.add(String.valueOf(reporte[1]));
            reporteLista.add(String.valueOf(reporte[2]));
            reporteLista.add(String.valueOf(reporte[3]));
        }

        return reporteLista;

    }
}
