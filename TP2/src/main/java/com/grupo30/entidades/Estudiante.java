package com.grupo30.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nro_libreta;

    @Column (nullable = false)
    private int documento;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String apellido;

    @Column (nullable = false)
    private int edad;

    @Column (nullable = false)
    private String genero;

    @Column (nullable = false)
    private String ciudad_residencia;

    @OneToMany (mappedBy = "estudiante",fetch = FetchType.LAZY)
    private List<Estudia> carreras;

    public Estudiante() {
        this.carreras = new ArrayList<>();
    }

    public Estudiante(int documento, String nombre, String apellido, int edad, String genero, String ciudad_residencia) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
        this.carreras = new ArrayList<>();
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    public List<Estudia> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Estudia> carreras) {
        this.carreras = carreras;
    }
}
