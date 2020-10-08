package model;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String userName;
    private String passWord;

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
        userID = -1; //undefined
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
