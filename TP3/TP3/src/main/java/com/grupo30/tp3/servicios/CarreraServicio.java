package com.grupo30.tp3.servicios;

import com.grupo30.tp3.model.Carrera;
import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CarreraServicio")
public class CarreraServicio implements BaseServicios<Carrera>{

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera findById(Long id) throws Exception {
        try{
            Optional<Carrera> carrera = carreraRepository.findById(id);
            return carrera.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        try{
            return carreraRepository.save(entity);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Carrera update(Long id, Carrera entity) throws Exception {
        try{
            Optional<Carrera> carrera = carreraRepository.findById(id);
            Carrera c = carrera.get();
            c = carreraRepository.save(c);
            return c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (carreraRepository.existsById(id)) {
                carreraRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("Carrera no encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
