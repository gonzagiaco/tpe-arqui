package DAO;

import Database.Conec;
import models.Factura;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturasImpl implements FacturasDAO{
    Connection con;
    String csv;

    public FacturasImpl() throws SQLException{
        this.csv = "src/main/java/csv/facturas.csv";
        con = Conec.getConnection();
    }


    @Override
    public void insertFacturas() {
        try{
            String[] HEADERS = {"idFactura", "idCliente"};
            Reader reader = new FileReader(this.csv);

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
            Iterable<CSVRecord> records = csvFormat.parse(reader);

            for(CSVRecord row : records){
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idCliente"));
                Factura f = new Factura(idFactura, idCliente);
                insertFactura(f);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertFactura(Factura f) {
        String sql = "INSERT INTO facturas (idFactura,idCliente) VALUES (?,?)";
        int id = f.getId();
        int clienteId = f.getId_cliente();

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, clienteId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
