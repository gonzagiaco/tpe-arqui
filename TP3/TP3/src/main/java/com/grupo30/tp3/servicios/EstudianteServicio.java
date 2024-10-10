package com.grupo30.tp3.servicios;

import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service ("EstudianteServicio")
public class EstudianteServicio implements BaseServicios<Estudiante>{

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAll() throws Exception {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante findById(Long id) throws Exception {
        try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
            return estudianteBuscado.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Estudiante save(Estudiante entity) throws Exception {
        try{
            return estudianteRepository.persist(entity);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Estudiante update(Long id, Estudiante entity) throws Exception {
        try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findByID(id);
            Estudiante e = estudianteBuscado.get();
            e = estudianteRepository.persist(e);
            return e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (estudianteRepository.existById(id)) {
                estudianteRepository.deleteByID(id);
                return true;
            } else {
                throw new Exception("Estudiante no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
