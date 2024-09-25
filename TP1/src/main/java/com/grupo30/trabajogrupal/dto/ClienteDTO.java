package com.grupo30.trabajogrupal.dto;

public class ClienteDTO {

    private int id;
    private String nombre;
    private String email;

    public ClienteDTO(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
