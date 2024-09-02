package DAO;

import models.Factura;
import models.Factura_Producto;

public interface FacturasProductosDAO {

    void insertFacturasProductos();

    void insertFacturaProducto(Factura_Producto f);
}
