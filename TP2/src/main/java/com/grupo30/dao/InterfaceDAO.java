package com.grupo30.dao;

public interface InterfaceDAO<T> {

    void insert(T entidad);

    void update(T entidad);

    void delete(int id);

    void insertAll(String csv);

    T select(int id);
}
