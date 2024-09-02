package app;


import DAO.ClienteImpl;
import Database.CreateTables;
import models.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) throws SQLException {
        //CreateTables tablas = new CreateTables()
        //tablas.createTables();

        ClienteImpl clienteDAO = new ClienteImpl();
        clienteDAO.insertClientes();
    }
}
