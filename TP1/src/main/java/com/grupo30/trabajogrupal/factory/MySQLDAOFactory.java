package com.grupo30.trabajogrupal.factory;

import com.grupo30.trabajogrupal.dao.ClienteImpl;
import com.grupo30.trabajogrupal.dao.FacturasImpl;
import com.grupo30.trabajogrupal.dao.FacturasProductosImpl;
import com.grupo30.trabajogrupal.dao.ProductosImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends AbstractFactory {
    private static MySQLDAOFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/tpgarqui";
    public static Connection conn;

    private MySQLDAOFactory() {
    }

    public static synchronized MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FacturasProductosImpl getFacturasProductosImpl() throws SQLException {
        return new FacturasProductosImpl(createConnection());
    }

    @Override
    public ProductosImpl getProductosImpl() throws SQLException {
        return new ProductosImpl(createConnection());
    }

    @Override
    public FacturasImpl getFacturasImpl() throws SQLException{
        return new FacturasImpl(createConnection());
    }

    @Override
    public ClienteImpl getClientesImpl() throws SQLException{
        return new ClienteImpl(createConnection());
    }

}

