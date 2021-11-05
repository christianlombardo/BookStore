package com.revature.dao;

import com.revature.model.Book;

import java.util.List;

public interface DAO<T> {

    boolean insert(T obj);

    T readById(T obj);

    List<T> readAll();

    T update(T obj);

    boolean delete(T obj);

    public List<Book> readAllByStringField(String tablefieldname, String name);
}
