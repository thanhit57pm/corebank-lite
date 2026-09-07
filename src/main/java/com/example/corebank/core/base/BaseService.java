package com.example.corebank.core.base;

import java.util.List;

public interface BaseService<T extends BaseEntity, ID> {
    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    void deleteById(ID id);
}
