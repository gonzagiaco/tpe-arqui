package com.grupo30.tp3.dtos;

import lombok.Getter;


@Getter
public class EstudianteDTO {

    private long documento;

    private String nombre;

    private String apellido;

    private int edad;

    public EstudianteDTO(long documento, String nombre, String apellido, int edad) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
}
