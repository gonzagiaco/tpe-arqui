package com.grupo30.trabajogrupal.dao;

import com.grupo30.trabajogrupal.factory.MySQLDAOFactory;
import com.grupo30.trabajogrupal.utils.CsvRecords;
import com.grupo30.trabajogrupal.dto.Factura_ProductoDTO;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturasProductosImpl implements EntidadDAO<Factura_ProductoDTO>{
    private Connection con;
    private String csv;

    public FacturasProductosImpl(Connection con) throws SQLException {
        this.csv = "src/main/resources/facturas-productos.csv";
        this.con = con;
    }

    @Override
    public void insertAll() {
        try{
            String[] HEADERS = {"idFactura", "idProducto", "cantidad"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, this.csv);

            for(CSVRecord row : records){
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));
                Factura_ProductoDTO fp = new Factura_ProductoDTO(idFactura, idProducto, cantidad);
                insert(fp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void insert(Factura_ProductoDTO fp) {
        String sql = "INSERT INTO factura_producto (factura_id, producto_id, cantidad) VALUES (?,?,?)";
        int idFactura = fp.getId_factura();
        int idProducto = fp.getId_producto();
        int cantidad = fp.getCantidad();

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            ps.close();
            con.commit();
        } catch (SQLException e) {
            System.err.println("Error al insertar registro: Factura ID = " + idFactura + ", ProductoDTO ID = " + idProducto);
            e.printStackTrace();
        }
    }
}
