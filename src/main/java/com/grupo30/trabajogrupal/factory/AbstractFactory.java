package com.grupo30.trabajogrupal.factory;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;

    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            default: return null;
        }
    }
}

