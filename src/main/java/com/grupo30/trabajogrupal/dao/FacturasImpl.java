package com.grupo30.trabajogrupal.dao;

import com.grupo30.trabajogrupal.factory.MySQLDAOFactory;
import com.grupo30.trabajogrupal.utils.CsvRecords;
import com.grupo30.trabajogrupal.dto.FacturaDTO;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturasImpl implements EntidadDAO<FacturaDTO>{
    private Connection con;
    private String csv;


    public FacturasImpl(Connection con) throws SQLException{

        this.csv = "src/main/resources/facturas.csv";
        this.con = con;
    }

    @Override
    public void insertAll() {
        try{
            String[] HEADERS = {"idFactura", "idCliente"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, this.csv);

            for(CSVRecord row : records){
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idCliente"));
                FacturaDTO f = new FacturaDTO(idFactura, idCliente);
                insert(f);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void insert(FacturaDTO f) {
        String sql = "INSERT INTO factura (id, cliente_id) VALUES (?,?)";
        int id = f.getId();
        int clienteId = f.getId_cliente();

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, clienteId);
            ps.executeUpdate();
            ps.close();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
