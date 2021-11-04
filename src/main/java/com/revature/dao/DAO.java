package com.revature.dao;

import java.util.List;

public interface DAO<T> {

    void insert(T obj);

    T readById(T obj);

    List<T> readAll();

    T update(T obj);

    boolean delete(T obj);
}
