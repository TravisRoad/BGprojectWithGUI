package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.UserDao;
import model.User;
import util.myexception.AccountNotExistException;
import util.myexception.WrongPassWdException;
import transport.Client;

public class LoginControl implements Initializable {

    private TextField userNameTextField;
    private TextField passWordTextField;
    private Button loginButton;
    private Button signinButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent event){
        String userName = userNameTextField.getText();
        String passWord = passWordTextField.getText();
        UserDao userDao = new UserDao();

        try {
            User user = userDao.search(userName,passWord); //TODO:获取到的用户还没有处理
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (AccountNotExistException e) {
            e.printStackTrace();
            System.out.println("账户不存在");
        } catch (WrongPassWdException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
    }


}
