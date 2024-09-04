package com.grupo30.trabajogrupal.dao;

import com.grupo30.trabajogrupal.dto.ProductoDTO;
import com.grupo30.trabajogrupal.factory.MySQLDAOFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class ProductosImpl implements ProductosDAO {
    String csv;
    Connection conn;
    public ProductosImpl() throws SQLException{
        this.csv="src/main/java/com.grupo30.trabajogrupal.csv/productos.com.grupo30.trabajogrupal.csv";
        conn= MySQLDAOFactory.createConnection();
    }
    @Override
    public void insertProductos() {
        try {
            String[] HEADERS = {"idProducto", "nombre", "valor"};

            Reader in = new FileReader(csv);

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
            Iterable<CSVRecord> records = csvFormat.parse(in);

            for(CSVRecord row: records) {
                int id = parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                float valor = Float.parseFloat(row.get("valor"));
                ProductoDTO p = new ProductoDTO(id, nombre, valor);
                insertProducto(p);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertProducto(ProductoDTO p) {
        String sql = "INSERT INTO producto VALUES(?,?,?)";
        int id = p.getId();
        String nombre = p.getNombre();
        float valor = p.getValor();

        try{
            PreparedStatement ps = this.conn.prepareStatement(sql);
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
