package com.grupo30.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Carrera {

    @Id
    private String nombre;
    @ManyToMany(fetch = FetchType.LAZY)
    List<Estudiante> estudiantes;

    public Carrera(){
        super();
        this.estudiantes = new ArrayList<>();
    }

    public Carrera(String nombre) {
        super();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
