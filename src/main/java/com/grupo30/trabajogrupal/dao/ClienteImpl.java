package com.grupo30.trabajogrupal.dao;

import com.grupo30.trabajogrupal.dto.ClienteDTO;
import com.grupo30.trabajogrupal.factory.MySQLDAOFactory;
import com.grupo30.trabajogrupal.utils.CsvRecords;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class ClienteImpl implements EntidadDAO<ClienteDTO> {
    private Connection con;
    private String csv;

    public ClienteImpl() throws SQLException {
        this.csv = "src/main/java/com.grupo30.trabajogrupal.csv/clientes.com.grupo30.trabajogrupal.csv";
        this.con = MySQLDAOFactory.createConnection();
    }

    @Override
    public void insertAll() {
        try {
            String[] HEADERS = {"idCliente", "nombre", "email"};

            CsvRecords csvRecords = new CsvRecords();
            Iterable<CSVRecord> records = csvRecords.getCsvRecords(HEADERS, this.csv);

            for(CSVRecord row: records) {
                int id = parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");
                ClienteDTO c = new ClienteDTO(id, nombre, email);
                insert(c);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(ClienteDTO c){
        String sql = "INSERT INTO cliente (id, nombre, email) VALUES(?,?,?)";
        int id = c.getId();
        String nombre = c.getNombre();
        String email = c.getEmail();

        try{
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, email);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
