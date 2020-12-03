package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import util.SecureHash;
import util.TransportThings;
import view.Home;
import view.Main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * 登录控制器
 */
public class LoginController implements Initializable {

    /**
     * 未登录就退出时设置状态码以及显示提示窗口
     *
     * @param loginStage 界面stage
     */
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

    /**
     * 构造方法，初始化界面元素以及绑定动作到按钮
     *
     * @param loginButton   登录按钮
     * @param signupButton  注册按钮
     * @param userField     用户名标签
     * @param passwordField 密码标签
     */
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

    /**
     * 当登录界面的login按钮被按下时，调用的登录方法
     *
     * @param userName 文本框中的用户名
     * @param passWord 文本框中的密码，发送前会加密
     * @param e        事件参数
     */
    public void login(String userName, String passWord, ActionEvent e) {
        TransportThings tt = new TransportThings();
        User user = new User(userName, passWord, 0);
        tt.setQuery("login");
        // 加密
        user.setPassWord(SecureHash.getResult(user.getPassWord()));
        tt.setUser(user);

        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();
        if (tt.getState() == 0x01) {
            System.out.println("success");
            user = tt.getUser();
            main.setUser(user);
            loginFlag = true;
            showAlert("Login Successful!", tt.getState(), null);
        } else if (tt.getState() == 0x00) {
            System.out.println(tt.getInfo());
            showAlert(tt.getInfo(), tt.getState(), null);
        }
    }

    /**
     * 当 {@Button sigh up} 被按下时，调用的方法
     *
     * @param userName 用户名
     * @param passWord 密码
     * @param e        事件
     */
    public void signup(String userName, String passWord, ActionEvent e) {
        TransportThings tt = new TransportThings();
        User user = new User(userName, passWord, 0);
        // 加密
        user.setPassWord(SecureHash.getResult(user.getPassWord()));
        tt.setQuery("signup");
        tt.setUser(user);
        if (passWord.length() < 6 || passWord.length() > 40) {
            showAlert("Password too short or too long", 0, null);
            return;
        }
        if (userName.length() > 50 || userName.length() == 0) {
            showAlert("Username is empty or too long", 0, null);
            return;
        }
        main.getClientTrans().writeObj(tt);//TODO:密码需要加密处理
        tt = (TransportThings) main.getClientTrans().readObj();
        if (tt.getState() == 0x01) {
            System.out.println("success");
            user = tt.getUser();
            showAlert("Signup Successful!", 0, null);
        } else if (tt.getState() == 0x00) {
            System.out.println(tt.getInfo());
            showAlert(tt.getInfo(), 0, null);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 提示窗口
     *
     * @param str   显示的内容
     * @param state 状态字段
     * @param e     事件
     */
    private void showAlert(String str, int state, Event e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //设置对话框标题
        alert.setTitle("Info");
        //设置内容
        alert.setHeaderText("CONFIRMATION".toLowerCase());
        alert.setContentText(str);
        //显示对话框
        Optional<ButtonType> result = alert.showAndWait();
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
