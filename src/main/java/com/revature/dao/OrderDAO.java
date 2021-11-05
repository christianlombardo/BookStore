package com.revature.dao;

import com.revature.model.Book;
import com.revature.model.Order;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO implements DAO<Order> {
    @Override
    public boolean insert(Order obj) {
        String sql = "insert into orders (username, sum_total, description) values (?,?,?)";

        int count=0;
        try {
            PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);

            ps.setString(1,obj.getUsername());
            ps.setDouble(2,obj.getTotal());
            ps.setString(3, obj.getDesc());
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (count <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public Order readById(Order obj) {
        String sql = "select * from orders where username = ?";
        Connection connection = ConnectionFactory.getConnection();
        Order order = new Order();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,obj.getUsername());

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setOrderId(rs.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public List<Order> readAll() {
        return null;
    }

    @Override
    public Order update(Order obj) {
        return null;
    }

    @Override
    public boolean delete(Order obj) {
        return false;
    }

    @Override
    public List<Book> readAllByStringField(String tablefieldname, String genre) {
        return null;
    }

    public void insertIntoJoin(int isbnNumber, int orderId) {
        String sql = "insert into orders_books (order_id, book_id) values (?,?);";

        try {
            PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ps.setInt(1,orderId);
            ps.setInt(2, isbnNumber);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
