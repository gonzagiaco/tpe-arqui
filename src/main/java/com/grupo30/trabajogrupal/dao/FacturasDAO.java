package com.grupo30.trabajogrupal.dao;

import com.grupo30.trabajogrupal.dto.FacturaDTO;

public interface FacturasDAO {

    void insertFacturas();

    void insertFactura(FacturaDTO facturaDTO);
}
