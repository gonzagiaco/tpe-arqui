package com.grupo30.trabajogrupal.dto;

public class FacturaDTO {

    private int id;
    public static int auto_id;
    private int id_cliente;

    public FacturaDTO(int id_cliente) {
        this.id = getAuto_id();
        this.id_cliente = id_cliente;
    }

    public FacturaDTO(int id_factura, int id_cliente) {
        this.id = id_factura;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    private static int getAuto_id() {
        return auto_id++;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
