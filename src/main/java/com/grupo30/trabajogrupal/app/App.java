package com.grupo30.trabajogrupal.app;


import com.grupo30.trabajogrupal.dto.*;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) throws SQLException {
        /*CREACION DE TABLAS E INSERCIÓN DE ELEMENTOS
        CreateTables tablas = new CreateTables();
        tablas.createTables();

        ClienteImpl clienteDAO = new ClienteImpl();
        clienteDAO.insertClientes();

        ProductosImpl productosDAO = new ProductosImpl();
        productosDAO.insertProductos();

        FacturasImpl facturasDAO = new FacturasImpl();
        facturasDAO.insertFacturas();

        FacturasProductosImpl facProdDAO = new FacturasProductosImpl();
        facProdDAO.insertFacturasProductos();*/

        Servicios servicios = new Servicios();

        // CONSIGNA N°3

        ProductoDTO p = servicios.getProductoMayorRecaudacion();
        System.out.println(p);

        // CONSIGNA N°4

        List<ClienteDTO> clientes = servicios.getListaClientesOrdenada();
        for (ClienteDTO cliente : clientes) {
            System.out.println(cliente);
        }
    }
}
