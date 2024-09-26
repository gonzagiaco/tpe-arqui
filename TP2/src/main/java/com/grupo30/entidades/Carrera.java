package com.grupo30.entidades;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String Nombre;

    @OneToMany (mappedBy = "carrera", fetch = FetchType.LAZY)
    private List<Estudia> estudiantes;

    public Carrera(String nombre) {
        Nombre = nombre;
        this.estudiantes = new ArrayList<Estudia>();
    }

    public int getId(){
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public List<Estudia> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudia> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
