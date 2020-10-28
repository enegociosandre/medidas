package br.com.andretecnologia.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudOperations<T> {

    T save(T object);

    T findById(Integer arg);

    List<T> findAll();

    void delete(Integer arg);

    T update(Integer var, T object);
}
