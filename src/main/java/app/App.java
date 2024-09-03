package app;


import DAO.*;
import Database.CreateTables;
import models.*;

import java.sql.Connection;
import java.sql.DriverManager;
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

        ServiciosImpl servicios = new ServiciosImpl();

        // CONSIGNA N°3

        Producto p = servicios.getProductoMayorRecaudacion();
        System.out.println(p);

        // CONSIGNA N°4

        List<Cliente> clientes = servicios.getListaClientesOrdenada();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
}
