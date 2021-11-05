package com.revature.service;

import com.revature.dao.BookDAO;
import com.revature.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() {

        List<Book> booksList = this.bookDAO.readAll();

        if (booksList.size() > 0) {
            return booksList;
        }

        return null;

    }

    public List<String> getAllGenres() {
        List<String> genreList = this.bookDAO.readByGenre();
        if (genreList.size() > 0) {
            return genreList;
        }

        return null;
    }

}
