package controller;

import dao.UserDao;

public class UserController {
    private static final UserDao ud = new UserDao();

    public boolean login(String userName, String passwd){
        return true;
    }
}
