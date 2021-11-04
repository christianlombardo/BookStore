package com.revature.service;

import com.revature.dao.BookDAO;
import com.revature.dao.UserDAO;
import com.revature.model.User;

public class UserService {

    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public boolean userLogin(User userLogin) {
        User user = this.userDao.readById(userLogin);

        if (userLogin.getPassword() != user.getPassword())
            return false;

        return true;
    }

}
