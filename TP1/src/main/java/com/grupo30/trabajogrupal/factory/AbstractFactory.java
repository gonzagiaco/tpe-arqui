package com.grupo30.trabajogrupal.factory;

import com.grupo30.trabajogrupal.dao.ClienteImpl;
import com.grupo30.trabajogrupal.dao.FacturasImpl;
import com.grupo30.trabajogrupal.dao.FacturasProductosImpl;
import com.grupo30.trabajogrupal.dao.ProductosImpl;

import java.sql.SQLException;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public abstract FacturasProductosImpl getFacturasProductosImpl() throws SQLException;
    public abstract ProductosImpl getProductosImpl() throws SQLException;
    public abstract FacturasImpl getFacturasImpl() throws SQLException;
    public abstract ClienteImpl getClientesImpl() throws SQLException;

    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }

}

