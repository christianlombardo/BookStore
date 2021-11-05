package com.revature.service;

import com.revature.dao.BookDAO;
import com.revature.dao.OrderDAO;
import com.revature.model.Book;
import com.revature.model.Order;

import java.util.List;

public class BookService {

    BookDAO bookDAO;
    OrderDAO orderDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
        this.orderDAO = new OrderDAO();
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

    public List<Book> getBooksByGenre(String genre) {
        List<Book> bookgenreList = this.bookDAO.readAllByStringField("genre_name", genre);
        if (bookgenreList.size() > 0) {
            return bookgenreList;
        }

        return null;
    }

    public Book getSingleBook(int isbn) {
        Book book = new Book();
        book.setIsbnNumber(isbn);
        return bookDAO.readById(book);
    }

    public void addOrder(Order order, List<Book> cart) {
        orderDAO.insert(order);
        order = orderDAO.readById(order);

        for(Book book : cart){
            orderDAO.insertIntoJoin(book.getIsbnNumber(), order.getOrderId());
        }
    }
}
