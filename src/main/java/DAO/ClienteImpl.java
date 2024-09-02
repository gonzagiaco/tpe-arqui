package DAO;

import Database.Conec;
import models.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ClienteImpl implements ClienteDAO {

    Connection con;
    String csv;

    public ClienteImpl() throws SQLException {
        this.csv = "src/main/java/csv/clientes.csv";
        con = Conec.getConnection();
    }

    @Override
    public void insertClientes() {
        try {
        String[] HEADERS = {"idCliente", "nombre", "email"};

            Reader in = new FileReader(csv);

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
            Iterable<CSVRecord> records = csvFormat.parse(in);

            for(CSVRecord row: records) {
                int id = parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");
                Cliente c = new Cliente(id, nombre, email);
                insertCliente(c);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertCliente(Cliente c){
        String sql = "INSERT INTO cliente VALUES(?,?,?)";
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
