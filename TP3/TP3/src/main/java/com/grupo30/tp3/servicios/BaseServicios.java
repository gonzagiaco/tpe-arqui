package com.grupo30.tp3.servicios;

import java.util.List;

public interface BaseServicios<E> {

    public List<E> findAll() throws Exception;

    public E findById(Long id) throws Exception;

    public E save(E entity) throws Exception;

    public E update(Long id, E entity) throws Exception;

    public boolean delete(Long id) throws Exception;
}
