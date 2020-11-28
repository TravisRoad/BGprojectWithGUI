package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.UserDao;
import model.User;
import transport.ClientTrans;
import util.Database;
import util.TransportThings;
import util.myexception.AccountNotExistException;
import util.myexception.WrongPassWdException;

public class LoginController extends ParentController implements Initializable {

    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passWordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent event){
        String userName = userNameTextField.getText();
        String passWord = passWordTextField.getText();
        TransportThings tt = new TransportThings();
        User user = new User(userName,passWord,0);
        tt.setQuery("login");
        tt.setUser(user);

        clientTrans.writeObj(tt);//TODO:密码需要加密处理
        tt = (TransportThings) clientTrans.readObj();
        if (tt.getState() == 0x01) {
            System.out.println("success");
            user = tt.getUser();
            mainPage.setCurrentUser(user);
        } else if (tt.getState() == 0x00) {
            System.out.println(tt.getInfo());
        }
    }

    public void signup(ActionEvent event) {
        String userName = userNameTextField.getText();
        String passWord = passWordTextField.getText();
        TransportThings tt = new TransportThings();
        User user = new User(userName, passWord, 0);
        tt.setQuery("signup");
        tt.setUser(user);

        clientTrans.writeObj(tt);//TODO:密码需要加密处理
        tt = (TransportThings) clientTrans.readObj();
        if (tt.getState() == 0x01) {
            System.out.println("success");
            user = tt.getUser();
        } else if (tt.getState() == 0x00) {
            System.out.println(tt.getInfo());
        }
    }
}
