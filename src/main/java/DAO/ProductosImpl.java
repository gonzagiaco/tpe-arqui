package DAO;

import models.Cliente;
import models.Producto;
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
        this.csv="src/main/java/csv/productos.csv";
        conn=Conec.getConnection();
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
                Producto p = new Producto(id, nombre, valor);
                insertProductos(p);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertProductos(Producto p) {
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
