package DAO;

import java.sql.Connection;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.Conec;


public class ServiciosImpl {

    private Connection con;
    public ServiciosImpl() throws SQLException{
        this.con = Conec.getConnection();

    }

    public Producto getProductoMayorRecaudacion(){
        String query = "SELECT p.id, p.nombre, p.valor, p.valor * SUM(fp.cantidad) AS recaudacion FROM producto p JOIN Factura_producto fp ON p.id = fp.producto_id GROUP BY p.id, p.valor ORDER BY recaudacion DESC LIMIT 1";
        Producto p = null;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                float valor = rs.getFloat("valor");
                //float recaudacion = rs.getFloat("recaudacion"); ¿Mostrarla en sout?

                p = new Producto(id, nombre, valor);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    public List<Cliente> getListaClientesOrdenada(){
        String query = "SELECT c.*, SUM(fp.cantidad * p.valor) AS total_facturado" +
                " FROM Cliente c" +
                " INNER JOIN Factura f ON c.id = f.cliente_id" +
                " INNER JOIN Factura_Producto fp ON f.id = fp.factura_id" +
                " INNER JOIN Producto p ON fp.producto_id = p.id" +
                " GROUP BY c.nombre" +
                " ORDER BY total_facturado DESC;";
        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String email = rs.getString(3);

                Cliente c = new Cliente(id, nombre, email);
                clientes.add(c);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
    }
}
