package com.grupo30.trabajogrupal.dao;

import com.grupo30.trabajogrupal.dto.ProductoDTO;

public interface ProductosDAO {

    void insertProductos();

    void insertProducto(ProductoDTO p);
}
