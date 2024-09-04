package com.grupo30.trabajogrupal.dao;


import com.grupo30.trabajogrupal.dto.ClienteDTO;

public interface ClienteDAO {

    void insertClientes();

    void insertCliente(ClienteDTO c);
}
