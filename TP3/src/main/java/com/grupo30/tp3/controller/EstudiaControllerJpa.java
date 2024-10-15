package com.grupo30.tp3.controller;

import com.grupo30.tp3.model.Carrera;
import com.grupo30.tp3.model.Estudia;
import com.grupo30.tp3.model.Estudiante;
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

    @GetMapping("/matricular/")
    public ResponseEntity<?> matricular(@RequestParam Carrera carrera, @RequestParam Estudiante estudiante) {
        try {
            int inscripcion = LocalDate.now().getYear();

            Estudia nuevaMatricula = new Estudia();
            nuevaMatricula.setEstudiante(estudiante);
            nuevaMatricula.setCarrera(carrera);
            nuevaMatricula.setInscripcion(inscripcion);
            estudiaServicio.matricularEstudiante(nuevaMatricula);
            return ResponseEntity.status(HttpStatus.OK).body("Estudiante matriculado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
