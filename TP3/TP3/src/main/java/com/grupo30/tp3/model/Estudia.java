package com.grupo30.tp3.model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Estudia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @Column
    private int inscripcion;

    @Column
    private int graduacion;

    @Column
    private int antiguedad;

    public Estudia() {
    }

    public Estudia(int id, Estudiante estudiante, Carrera carrera, int inscripcion, int graduacion, int antiguedad) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

}
