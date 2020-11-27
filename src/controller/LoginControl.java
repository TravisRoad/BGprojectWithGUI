package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.UserDao;
import model.User;
import transport.ClientTrans;
import util.TransportThings;
import util.myexception.AccountNotExistException;
import util.myexception.WrongPassWdException;

public class LoginControl implements Initializable {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passWordTextField;
    @FXML
    private Button loginButton;
    private Button signinButton;

    private ClientTrans clientTrans;//在创建loginPane时已经传入参数

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent event){
        String userName = userNameTextField.getText();
        String passWord = passWordTextField.getText();
        TransportThings tt = new TransportThings();
        tt.setQuery("login");

        clientTrans.writeObj(tt);
    }

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }
}
