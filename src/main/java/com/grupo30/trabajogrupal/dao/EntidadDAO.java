package com.grupo30.trabajogrupal.dao;


public interface EntidadDAO<T> {

    void insertAll();

    void insert(T entidad);
}
