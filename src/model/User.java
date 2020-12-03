package model;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private long userID;
    private String userName;
    private String passWord;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;

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

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
