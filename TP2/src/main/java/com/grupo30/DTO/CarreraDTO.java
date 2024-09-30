package com.grupo30.DTO;

import com.grupo30.entidades.Estudia;
import com.grupo30.entidades.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class CarreraDTO {

    private String nombre;
    private int cantidadEstudiantes;

    public CarreraDTO(String nombre, int cantidadEstudiantes) {
        this.nombre = nombre;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombre='" + nombre + '\'' +
                ", estudiantes=" + cantidadEstudiantes +
                '}';
    }
}
