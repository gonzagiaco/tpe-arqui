package DAO;

import Database.Conec;
import csv.CsvRecords;
import models.Producto;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class ProductosImpl implements ProductosDAO {
    private String csv;
    private Connection con;

    public ProductosImpl() throws SQLException{
        this.csv="src/main/java/csv/productos.csv";
        this.con= Conec.getConnection();
    }
    @Override
    public void insertProductos() {
        try {
            String[] HEADERS = {"idProducto", "nombre", "valor"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, this.csv);

            for(CSVRecord row: records) {
                int id = parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                float valor = Float.parseFloat(row.get("valor"));
                Producto p = new Producto(id, nombre, valor);
                insertProductos(p);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertProductos(Producto p) {
        String sql = "INSERT INTO producto (id, nombre, valor) VALUES(?,?,?)";
        int id = p.getId();
        String nombre = p.getNombre();
        float valor = p.getValor();

        try{
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setFloat(3, valor);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
