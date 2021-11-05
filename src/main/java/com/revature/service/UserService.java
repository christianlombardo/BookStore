package com.revature.service;

import com.revature.dao.BookDAO;
import com.revature.dao.UserDAO;
import com.revature.model.User;

public class UserService {

    private final UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public boolean userRegister(User user) {

        if (this.userDao.insert(user))
            return true;

        return false;
    }


    public User userLogin(User userLogin) {
        User user = this.userDao.readById(userLogin);

        if (userLogin.getPassword().equals(user.getPassword())) {
            return user;
        }
        else {
            return null;
        }

    }




}
