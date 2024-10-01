package com.grupo30.entidades;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {

    @Id
    private int id;

    @Column
    private String carrera;

    @Column
    private int duracion;

    @OneToMany (mappedBy = "carrera", fetch = FetchType.LAZY)
    private List<Estudia> estudiantes;

    public Carrera() {
        this.estudiantes = new ArrayList<>(); // Inicializando la lista
    }

    public Carrera(int id, String carrera, int duracion) {
        this.id = id;
        this.carrera = carrera;
        this.duracion = duracion;
        this.estudiantes = new ArrayList<Estudia>();
    }

    public int getId(){
        return id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<Estudia> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudia> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", carrera='" + carrera + '\'' +
                ", duracion=" + duracion +
                ", estudiantes=" + estudiantes +
                '}';
    }
}
