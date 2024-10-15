package com.grupo30.tp3.dtos;

import lombok.Getter;

@Getter
public class CarreraDTO {

    private String carrera;

    private int duracion;

    private Long inscriptos;

    public CarreraDTO(String carrera, int duracion, Long inscriptos) {
        this.carrera = carrera;
        this.duracion = duracion;
        this.inscriptos = inscriptos;
    }
}
