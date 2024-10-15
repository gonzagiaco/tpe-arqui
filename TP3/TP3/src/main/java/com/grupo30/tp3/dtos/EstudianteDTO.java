package com.grupo30.tp3.dtos;

import lombok.Getter;


@Getter
public class EstudianteDTO {

    private long documento;

    private String nombre;

    private String apellido;

    private int edad;

    private String genero;

    private String ciudad;

    public EstudianteDTO(long documento, String nombre, String apellido, int edad, String genero, String ciudad) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
    }
}
