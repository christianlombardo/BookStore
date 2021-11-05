package com.revature.dao;

import com.revature.model.Book;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements DAO<Book>{
    @Override
    public boolean insert(Book obj) {

        return false;
    }

    @Override
    public Book readById(Book obj) {
        String sql = "select * from books where isbnNumber = ?";
        Connection connection = ConnectionFactory.getConnection();
        Book book = new Book();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,obj.getIsbnNumber());

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                book.setIsbnNumber(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setGenre(rs.getString(4));
                book.setPrice(rs.getDouble(5));
                book.setDesc(rs.getString(6));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return book;
    }

    public List<String> readByGenre(){
        String sql = "select distinct genre_name from books";
        Connection connection = ConnectionFactory.getConnection();
        List<String> genres = new ArrayList<>();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                genres.add(rs.getString(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public List<Book> readAllByStringField(String tablefieldname, String genre){
        String sql = "select * from books where ? = ?";
        Connection connection = ConnectionFactory.getConnection();
        List<Book> genres = new ArrayList<>();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tablefieldname);
            ps.setString(2, genre);
            sql.toString();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setIsbnNumber(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setGenre(rs.getString(4));
                book.setPrice(rs.getDouble(5));
                book.setDesc(rs.getString(6));
                genres.add(book);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public List<Book> readAll() {
        String sql = "select * from books";
        Connection connection = ConnectionFactory.getConnection();
        List<Book> books = new ArrayList<>();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setIsbnNumber(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setGenre(rs.getString(4));
                book.setPrice(rs.getDouble(5));
                book.setDesc(rs.getString(6));
                books.add(book);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }



    @Override
    public Book update(Book obj) {
        return null;
    }

    @Override
    public boolean delete(Book obj) {
        return false;
    }
}
