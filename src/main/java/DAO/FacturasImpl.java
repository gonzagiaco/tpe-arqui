package DAO;

import Database.Conec;
import csv.CsvRecords;
import models.Factura;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturasImpl implements FacturasDAO{
    private Connection con;
    private String csv;

    public FacturasImpl() throws SQLException{
        this.csv = "src/main/java/csv/facturas.csv";
        this.con = Conec.getConnection();
    }

    @Override
    public void insertFacturas() {
        try{
            String[] HEADERS = {"idFactura", "idCliente"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, this.csv);

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
        String sql = "INSERT INTO facturas (id, cliente_id) VALUES (?,?)";
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
