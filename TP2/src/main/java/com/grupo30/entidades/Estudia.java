package com.grupo30.entidades;

import javax.persistence.*;

@Entity
public class Estudia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "libreta_alumno")
    private Estudiante estudiante;

    @ManyToOne
    private Carrera carrera;

    @Column
    private Boolean graduado;

    @Column
    private int antiguedad;

    public Estudia(Estudiante estudiante, Carrera carrera, Boolean graduado, int antiguedad) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.graduado = graduado;
        this.antiguedad = antiguedad;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Boolean getGraduado() {
        return graduado;
    }

    public void setGraduado(Boolean graduado) {
        this.graduado = graduado;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
