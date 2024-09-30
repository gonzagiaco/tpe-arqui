package com.grupo30.repository;

import com.grupo30.DTO.CarreraDTO;
import com.grupo30.dao.CarreraDAO;
import com.grupo30.entidades.Carrera;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepository {

    private CarreraDAO carreraDAO;

    public CarreraRepository(EntityManager em) {
        this.carreraDAO = new CarreraDAO(em);
    }

    public void darDeAltaCarrera(Carrera c){
        this.carreraDAO.insert(c);
    }

    public Carrera buscarCarreraPorID(int id){
        return this.carreraDAO.select(id);
    }

    public List<CarreraDTO> selectCarrerasConEstudiantesOrdenadas(){
        List<Carrera> carreras = this.carreraDAO.selectCarrerasConEstudiantesOrdenadas();
        List<CarreraDTO> carrerasDTO = new ArrayList<CarreraDTO>();

        for(Carrera c : carreras){
            CarreraDTO cDTO = new CarreraDTO(c.getNombre());
            cDTO.setEstudiantes(c.getEstudiantes());
        }

        return carrerasDTO;
    }
}
