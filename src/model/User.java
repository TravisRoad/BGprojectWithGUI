package model;

public class User {
    private int userID;
    private String userName;
    private String passWord;

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
        userID = -1; //undefined
    }

}
