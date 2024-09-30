package com.grupo30.entidades;

import javax.persistence.*;
import java.sql.Date;

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
    private Date antiguedad;

    public Estudia() {
    }

    public Estudia(Estudiante estudiante, Carrera carrera, Boolean graduado, Date antiguedad) {
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

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }
}
