package DAO;

import Database.Conec;
import csv.CsvRecords;
import models.Factura_Producto;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturasProductosImpl implements FacturasProductosDAO{
    private Connection con;
    private String csv;

    FacturasProductosImpl() throws SQLException {
        this.csv = "src/main/java/csv/facturas-productos.csv";
        this.con = Conec.getConnection();
    }

    @Override
    public void insertFacturasProductos() {
        try{
            String[] HEADERS = {"idFactura", "idProducto", "cantidad"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, this.csv);

            for(CSVRecord row : records){
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));
                Factura_Producto fp = new Factura_Producto(idFactura, idCliente, cantidad);
                insertFacturasProducto(fp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void insertFacturasProducto(Factura_Producto fp) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
