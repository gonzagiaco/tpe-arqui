package models;

public class Producto {

    private int id;
    public static int auto_id;
    private String nombre;
    private float valor;

    public Producto(String nombre, float valor) {
        this.id = getAuto_id();
        this.nombre = nombre;
        this.valor = valor;
    }

    public Producto(int id, String nombre, float valor) {
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
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
