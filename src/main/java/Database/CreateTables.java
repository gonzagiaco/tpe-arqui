package Database;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTables {


    private static void createTables(Connection con) throws SQLException {
        String query = "-- Created by Vertabelo (http://vertabelo.com)\n" +
                "-- Last modification date: 2024-08-31 19:56:13.103\n" +
                "\n" +
                "-- tables\n" +
                "-- Table: cliente\n" +
                "CREATE TABLE cliente (\n" +
                "    id int  NOT NULL,\n" +
                "    nombre varchar(500)  NOT NULL,\n" +
                "    email varchar(150)  NOT NULL,\n" +
                "    CONSTRAINT cliente_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "-- Table: factura\n" +
                "CREATE TABLE factura (\n" +
                "    id int  NOT NULL,\n" +
                "    cliente_id int  NOT NULL,\n" +
                "    CONSTRAINT factura_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "-- Table: factura_producto\n" +
                "CREATE TABLE factura_producto (\n" +
                "    cantidad int  NOT NULL,\n" +
                "    factura_id int  NOT NULL,\n" +
                "    producto_id int  NOT NULL,\n" +
                "    CONSTRAINT factura_producto_pk PRIMARY KEY (factura_id,producto_id)\n" +
                ");\n" +
                "\n" +
                "-- Table: producto\n" +
                "CREATE TABLE producto (\n" +
                "    id int  NOT NULL,\n" +
                "    nombre varchar(45)  NOT NULL,\n" +
                "    valor float  NOT NULL,\n" +
                "    CONSTRAINT producto_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "-- foreign keys\n" +
                "-- Reference: factura_cliente (table: factura)\n" +
                "ALTER TABLE factura ADD CONSTRAINT factura_cliente FOREIGN KEY factura_cliente (cliente_id)\n" +
                "    REFERENCES cliente (id);\n" +
                "\n" +
                "-- Reference: factura_producto_factura (table: factura_producto)\n" +
                "ALTER TABLE factura_producto ADD CONSTRAINT factura_producto_factura FOREIGN KEY factura_producto_factura (factura_id)\n" +
                "    REFERENCES factura (id);\n" +
                "\n" +
                "-- Reference: factura_producto_producto (table: factura_producto)\n" +
                "ALTER TABLE factura_producto ADD CONSTRAINT factura_producto_producto FOREIGN KEY factura_producto_producto (producto_id)\n" +
                "    REFERENCES producto (id);\n" +
                "\n" +
                "-- End of file.";

        con.prepareStatement(query).execute();
        con.commit();
        con.close();


    }
}
