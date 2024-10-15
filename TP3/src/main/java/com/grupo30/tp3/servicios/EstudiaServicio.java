package com.grupo30.tp3.servicios;

import com.grupo30.tp3.model.Estudia;
import com.grupo30.tp3.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EstudiaServicio")
public class EstudiaServicio implements BaseServicios<Estudia>{

    @Autowired
    private EstudianteCarreraRepository esRepository;

    @Override
    public List<Estudia> findAll() throws Exception {
        return esRepository.findAll();
    }

    @Override
    public Estudia findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Estudia save(Estudia entity) throws Exception {
        try{
            return esRepository.save(entity);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Estudia update(Long id, Estudia entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
