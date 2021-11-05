package com.revature.dao;

import com.revature.model.Order;
import com.revature.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO implements DAO<Order> {
    @Override
    public boolean insert(Order obj) {
        String sql = "insert into orders (user_id, sum_total, description) values (?,?,?)";

        int count=0;
        try {
            PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);

            ps.setInt(1,obj.getUserId());
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
        return null;
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
}
