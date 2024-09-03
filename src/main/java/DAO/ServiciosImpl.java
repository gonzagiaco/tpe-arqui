package DAO;

import java.sql.Connection;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
