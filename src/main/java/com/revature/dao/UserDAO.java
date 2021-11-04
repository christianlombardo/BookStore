package com.revature.dao;

import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements DAO<User>{
    @Override
    public void insert(User obj) {
        String sql = "insert into users (name, username, password) values (?,?,?)";

        try {
            PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User readById(User obj) {
        String sql = "select * from users where username = ?;";
        User user = new User();

        try {
            PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ps.setString(1, obj.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setName(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setPassword(rs.getString(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public boolean delete(User obj) {
        return false;
    }
}
