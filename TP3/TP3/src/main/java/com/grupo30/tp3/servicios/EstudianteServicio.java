package com.grupo30.tp3.servicios;

import com.grupo30.tp3.dtos.EstudianteDTO;
import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
            Estudiante e = estudianteBuscado.get();
            return e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Estudiante save(Estudiante entity) throws Exception {
        try{
            return estudianteRepository.save(entity);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Estudiante update(Long id, Estudiante entity) throws Exception {
        try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
            Estudiante e = estudianteBuscado.get();
            e = estudianteRepository.save(e);
            return e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (estudianteRepository.existsById(id)) {
                estudianteRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("Estudiante no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Obtener estudiante por nro de libretao dado
    @Transactional(readOnly = true)
    public EstudianteDTO buscarEstudiantePorNroLibreta(int nroLibreta) throws Exception {
        try {
            Optional<Estudiante> estudiante = estudianteRepository.findByNroLibreta(nroLibreta);
            Estudiante e = estudiante.get();
            return new EstudianteDTO(e.getDocumento(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad_residencia());
        } catch (NoSuchElementException e) {
            throw new Exception("Estudiante no encontrado: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Error al buscar estudiante por número de libreta: " + e.getMessage(), e);
        }
    }

    // Obtener estudiantes ordenados por criterio simple
    @Transactional(readOnly = true)
    public List<EstudianteDTO> getEstudiantesOrdenados(String orden) {
        List<String> camposValidos = Arrays.asList("nombre", "apellido", "edad");

        // Usa 'nombre' como criterio por defecto si el valor no es válido
        if (!camposValidos.contains(orden)) {
            orden = "nombre";
        }

        Sort sort = Sort.by(orden);
        List<Estudiante> estudiantes = estudianteRepository.findAll(sort);
        return estudiantes.stream()
                .map(estudiante -> new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(), estudiante.getGenero(), estudiante.getCiudad_residencia()))
                .collect(Collectors.toList());
    }
    //Obtener todos los estudiantes por genero
    @Transactional(readOnly = true)
    public List<EstudianteDTO> getEstudianteByGenero(String genero) throws Exception {
        try {
            List<Estudiante> estudiantes = estudianteRepository.findByGenero(genero);
            return estudiantes.stream()
                    .map(e -> new EstudianteDTO(e.getDocumento(), e.getNombre(), e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad_residencia()))
                    .collect(Collectors.toList());

        }catch (NoSuchElementException e) {
            throw new Exception("Estudiantes no encontrados: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Error al buscar estudiantes por genero: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> getEstudianteByCarreraFilterCiudad(String carrera, String ciudad) throws Exception {
        try {
            List<Estudiante> estudiantes = estudianteRepository.findByCarreraFilterCiudad(carrera, ciudad);
            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante estudiante : estudiantes) {
                estudianteDTOS.add(new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(), estudiante.getGenero(), estudiante.getCiudad_residencia()));
            }

            return estudianteDTOS;

        }catch (NoSuchElementException e) {
            throw new Exception("Estudiantes no encontrados: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Error al buscar estudiantes por carrera y filtrados por genero: " + e.getMessage(), e);
        }
    }
}
