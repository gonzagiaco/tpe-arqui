package com.grupo30.tp3.servicios;

import com.grupo30.tp3.dtos.EstudianteDTO;
import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    // Obtener estudiante por nro de libretao dado
    @Transactional(readOnly = true)
    public EstudianteDTO buscarEstudiantePorNroLibreta(int nroLibreta) throws Exception {
        try {
            var optionalEstudiante = estudianteRepository.findByNroLibreta(nroLibreta);
            return optionalEstudiante.map(estudiante ->
                            new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad()))
                    .orElseThrow(() -> new Exception("No se encontró ningún estudiante con el número de libreta: " + nroLibreta));
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
                .map(estudiante -> new EstudianteDTO(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad()))
                .collect(Collectors.toList());
    }

}
