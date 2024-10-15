package com.grupo30.tp3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Estudiante {
    @Id
    private long documento;

    @Column(nullable = false)
    private int nro_libreta;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String ciudad_residencia;

    @JsonIgnore
    @OneToMany(mappedBy = "estudiante",fetch = FetchType.LAZY)
    private List<Estudia> carreras;

    public Estudiante() {
        //this.carreras = new ArrayList<>();
    }

    public Estudiante(long documento, String nombre, String apellido, int edad, String genero, String ciudad_residencia, int nro_libreta) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
        //this.carreras = new ArrayList<>();
        this.nro_libreta = nro_libreta;
    }

    public int getNro_libreta() {
        return nro_libreta;
    }

    public void setNro_libreta(int nro_libreta) {
        this.nro_libreta = nro_libreta;
    }

    public long getDocumento() {
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

    @Override
    public String toString() {
        return "Estudiante{" +
                "documento=" + documento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudad_residencia='" + ciudad_residencia + '\'' +
                ", nro_libreta=" + nro_libreta +
                '}';
    }
}
