package com.grupo30.tp3.controller;


import com.grupo30.tp3.servicios.CarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carreras")
public class CarreraControllerJpa {

    @Autowired
    private CarreraServicio carreraServicio;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraServicio.findAll());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
