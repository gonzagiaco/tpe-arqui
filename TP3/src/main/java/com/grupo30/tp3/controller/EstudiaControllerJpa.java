package com.grupo30.tp3.controller;

import com.grupo30.tp3.model.Carrera;
import com.grupo30.tp3.model.Estudia;
import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.repository.CarreraRepository;
import com.grupo30.tp3.repository.EstudianteRepository;
import com.grupo30.tp3.servicios.EstudiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/matriculados")
public class EstudiaControllerJpa {

    @Autowired
    private EstudiaServicio estudiaServicio;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository  carreraRepository;

    @GetMapping("")
    public ResponseEntity<?> findAll() throws Exception {
        System.out.println(estudiaServicio.findAll());
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudiaServicio.findAll());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    @GetMapping("/matricular")
    public ResponseEntity<?> matricular(
            @RequestParam (value = "carreraID") Long carreraId,
            @RequestParam (value = "alumnoID") Long estudianteDocumento) {
        try {
            int inscripcion = LocalDate.now().getYear();

            Carrera carrera = carreraRepository.findById(carreraId).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

            Estudiante estudiante = estudianteRepository.findById(estudianteDocumento).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

            Estudia nuevaMatricula = new Estudia();
            nuevaMatricula.setEstudiante(estudiante);
            nuevaMatricula.setAntiguedad(0);
            nuevaMatricula.setGraduacion(0);
            nuevaMatricula.setCarrera(carrera);
            nuevaMatricula.setInscripcion(inscripcion);
            estudiaServicio.save(nuevaMatricula);
            return ResponseEntity.status(HttpStatus.OK).body("Estudiante matriculado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
