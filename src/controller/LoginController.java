package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.UserDao;
import javafx.stage.Stage;
import model.User;
import transport.ClientTrans;
import util.Database;
import util.TransportThings;
import util.myexception.AccountNotExistException;
import util.myexception.WrongPassWdException;
import view.Home;
import view.Main;

public class LoginController implements Initializable {

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
        loginStage.setOnCloseRequest(e -> {
            showAlert("本程序必须登录使用，确认退出吗？", 0x02, e);
        });
    }

    private Stage loginStage;
    private Button loginButton;
    private Button signupButton;
    private TextField userField;
    private PasswordField passwordField;

    private boolean loginFlag;


    Main main;

    public LoginController(Button loginButton, Button signupButton, TextField userField, PasswordField passwordField) {
        this.loginButton = loginButton;
        this.signupButton = signupButton;
        this.userField = userField;
        this.passwordField = passwordField;
        loginFlag = false;
        loginButton.setOnAction(e -> {
            login(userField.getText(), passwordField.getText(), e);
        });
        signupButton.setOnAction(e -> {
            signup(userField.getText(), passwordField.getText(), e);
        });
        loginButton.setDefaultButton(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(String userName, String passWord, ActionEvent e) {
        TransportThings tt = new TransportThings();
        User user = new User(userName, passWord, 0);
        tt.setQuery("login");
        tt.setUser(user);

        main.getClientTrans().writeObj(tt);//TODO:密码需要加密处理
        tt = (TransportThings) main.getClientTrans().readObj();
        if (tt.getState() == 0x01) {
            System.out.println("success");
            user = tt.getUser();
            main.setUser(user);
            loginFlag = true;
            showAlert("登陆成功", tt.getState(), null);
        } else if (tt.getState() == 0x00) {
            System.out.println(tt.getInfo());
            showAlert(tt.getInfo(), tt.getState(), null);
        }
    }

    public void signup(String userName, String passWord, ActionEvent e) {
        TransportThings tt = new TransportThings();
        User user = new User(userName, passWord, 0);
        tt.setQuery("signup");
        tt.setUser(user);
        if (passWord.length() < 6 || passWord.length() > 40) {
            showAlert("密码长度不合要求", 0, null);
            return;
        }
        if (userName.length() > 50 || userName.length() == 0) {
            showAlert("用户名不符合要求", 0, null);
            return;
        }
        main.getClientTrans().writeObj(tt);//TODO:密码需要加密处理
        tt = (TransportThings) main.getClientTrans().readObj();
        if (tt.getState() == 0x01) {
            System.out.println("success");
            user = tt.getUser();
            showAlert("注册成功", 0, null);
        } else if (tt.getState() == 0x00) {
            System.out.println(tt.getInfo());
            showAlert(tt.getInfo(), 0, null);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    private void showAlert(String str, int state, Event e) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        //设置对话框标题
        alert2.setTitle("Exit");
        //设置内容
        alert2.setHeaderText(str);
        //显示对话框
        Optional<ButtonType> result = alert2.showAndWait();
        switch (state) {
            case 0x01:
                if (result.get() == ButtonType.OK) {
                    loginStage.close();
                    Home home = new Home();
                    home.setMain(main);
                    home.start();
                }
                break;
            case 0x00:
                break;
            case 0x02:
                if (result.get() == ButtonType.OK && !loginFlag) {
                    System.exit(0x01);
                } else {
                    e.consume();
                }
                break;
            default:
                break;
        }
    }
}
