package com.grupo30.tp3.controller;


import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.servicios.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Estudiante")
public class EstudianteControllerJpa {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findAll());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    /**
     * Dar de alta a un estudiante
     * @param estudiante
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.save(estudiante));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

}
