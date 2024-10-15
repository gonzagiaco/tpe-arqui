package com.grupo30.tp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface RepoBase<T, ID> extends JpaRepository<T, ID> {


    void delete (T deleted);

    List<T> findAll();

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    void deleteById(ID id);
}
