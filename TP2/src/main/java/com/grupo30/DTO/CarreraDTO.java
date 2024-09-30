package com.grupo30.DTO;

import com.grupo30.entidades.Estudia;
import com.grupo30.entidades.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class CarreraDTO {

    private String nombre;
    private List<Estudia> estudiantes;

    public CarreraDTO(String nombre) {
    this.nombre = nombre;
    this.estudiantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudia> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudia> estudiantes) {
        this.estudiantes.addAll(estudiantes);
    }

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombre='" + nombre + '\'' +
                ", estudiantes=" + estudiantes +
                '}';
    }
}
