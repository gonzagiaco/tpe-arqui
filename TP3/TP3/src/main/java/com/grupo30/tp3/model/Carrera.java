package com.grupo30.tp3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String carrera;

    @Column
    private int duracion;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    private List<Estudia> estudiantes = new ArrayList<>();

    public Carrera() {
        this.estudiantes = new ArrayList<>(); // Inicializando la lista
    }

    public Carrera(long id, String carrera, int duracion) {
        this.id = id;
        this.carrera = carrera;
        this.duracion = duracion;
    }

    public long getId(){
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

    public void setId(long id) {
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
