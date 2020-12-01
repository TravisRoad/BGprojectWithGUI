package model;

import java.io.Serializable;

public class User implements Serializable {
    private long userID;
    private String userName;
    private String passWord;

    public User(String userName, String passWord, long userID) {
        this.userName = userName;
        this.passWord = passWord;
        this.userID = userID;
    }

    public User() {
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

    public String getUserName() {
        return userName;
    }

    public long getUserID() {
        return userID;
    }

    public String getPassWord() {
        return passWord;
    }
}
