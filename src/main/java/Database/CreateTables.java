package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    private final Connection con = Conec.getConnection();

    public CreateTables() throws SQLException {}

    public void createTables() throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();

            String clienteTable = "CREATE TABLE IF NOT EXISTS cliente (" +
                    "id int NOT NULL," +
                    "nombre varchar(500) NOT NULL," +
                    "email varchar(150) NOT NULL," +
                    "CONSTRAINT cliente_pk PRIMARY KEY (id));";

            String facturaTable = "CREATE TABLE IF NOT EXISTS factura (" +
                    "id int NOT NULL," +
                    "cliente_id int NOT NULL," +
                    "CONSTRAINT factura_pk PRIMARY KEY (id));";

            String productoTable = "CREATE TABLE IF NOT EXISTS producto (" +
                    "id int NOT NULL," +
                    "nombre varchar(45) NOT NULL," +
                    "valor float NOT NULL," +
                    "CONSTRAINT producto_pk PRIMARY KEY (id));";

            String facturaProductoTable = "CREATE TABLE IF NOT EXISTS factura_producto (" +
                    "cantidad int NOT NULL," +
                    "factura_id int NOT NULL," +
                    "producto_id int NOT NULL," +
                    "CONSTRAINT factura_producto_pk PRIMARY KEY (factura_id,producto_id));";

            String facturaForeignKey = "ALTER TABLE factura " +
                    "ADD CONSTRAINT factura_cliente " +
                    "FOREIGN KEY (cliente_id) " +
                    "REFERENCES cliente(id);";

            String facturaProductoFacturaFK = "ALTER TABLE factura_producto " +
                    "ADD CONSTRAINT factura_producto_factura " +
                    "FOREIGN KEY (factura_id) " +
                    "REFERENCES factura(id);";

            String facturaProductoProductoFK = "ALTER TABLE factura_producto " +
                    "ADD CONSTRAINT factura_producto_producto " +
                    "FOREIGN KEY (producto_id) " +
                    "REFERENCES producto(id);";

            // Ejecutar las consultas
            stmt.execute(clienteTable);
            stmt.execute(facturaTable);
            stmt.execute(productoTable);
            stmt.execute(facturaProductoTable);
            stmt.execute(facturaForeignKey);
            stmt.execute(facturaProductoFacturaFK);
            stmt.execute(facturaProductoProductoFK);

            // Hacer commit de los cambios si autocommit está deshabilitado
            //con.commit(); // No necesario si autocommit está habilitado (por defecto en MySQL)

        } catch (SQLException e) {
            if (con != null) {
                con.rollback(); // Deshacer cambios si ocurre un error
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close(); // Cerrar conexión después de su uso
            }
        }
    }
}
