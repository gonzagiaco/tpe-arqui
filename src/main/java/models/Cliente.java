package models;

public class Cliente {

    private int id;
    public static int auto_id; //preguntar
    private String nombre;
    private String email;

    public Cliente(String nombre, String email) {
        this.id = getAuto_id();
        this.nombre = nombre;
        this.email = email;
    }

    private static int getAuto_id() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
