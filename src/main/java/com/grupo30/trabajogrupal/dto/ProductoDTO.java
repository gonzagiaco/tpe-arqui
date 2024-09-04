package com.grupo30.trabajogrupal.dto;

public class ProductoDTO {

    private int id;
    public static int auto_id;
    private String nombre;
    private float valor;

    public ProductoDTO(String nombre, float valor) {
        this.id = getAuto_id();
        this.nombre = nombre;
        this.valor = valor;
    }

    public ProductoDTO(int id, String nombre, float valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public static int getAuto_id() {
        return auto_id++;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
