package com.grupo30.trabajogrupal.app;


import com.grupo30.trabajogrupal.dto.*;
import com.grupo30.trabajogrupal.factory.CreateTables;
import com.grupo30.trabajogrupal.dao.*;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) throws SQLException {
        CreateTables tablas = new CreateTables();
        tablas.createTables();

        ClienteImpl clienteDAO = new ClienteImpl();
        clienteDAO.insertAll();

        ProductosImpl productosDAO = new ProductosImpl();
        productosDAO.insertAll();

        FacturasImpl facturasDAO = new FacturasImpl();
        facturasDAO.insertAll();

        FacturasProductosImpl facProdDAO = new FacturasProductosImpl();
        facProdDAO.insertAll();

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
