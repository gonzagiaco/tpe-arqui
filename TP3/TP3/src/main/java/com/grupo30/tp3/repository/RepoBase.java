package com.grupo30.tp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RepoBase<T, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findAll();

    Optional<T> findByID(Long id);

    boolean existById(Long id);

    void deleteByID(Long id);

    T persist(T persisted);
}
