package com.grupo30.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nro_libreta;
    @Column
    private int documento;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String ciudad_residencia;
    @ManyToMany (mappedBy = "estudiantes", fetch = FetchType.LAZY)
    List<Carrera> carrera;

    public Estudiante(){
        super();
        this.carrera = new ArrayList<>();
    }

    public Estudiante(int documento, String nombre, String apellido, int edad, String ciudad_residencia) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad_residencia = ciudad_residencia;
    }

    public int getNro_libreta() {
        return nro_libreta;
    }

    public void setNro_libreta(int nro_libreta) {
        this.nro_libreta = nro_libreta;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    public List<Carrera> getCarrera() {
        return carrera;
    }

    public void setCarrera(List<Carrera> carrera) {
        this.carrera = carrera;
    }
}
