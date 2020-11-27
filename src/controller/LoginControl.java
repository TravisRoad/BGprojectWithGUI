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
import util.Database;
import util.TransportThings;
import util.myexception.AccountNotExistException;
import util.myexception.WrongPassWdException;

public class LoginControl extends ParentController implements Initializable {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passWordTextField;
    @FXML
    private Button loginButton;
    private Button signinButton;

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
        if(tt.getState()==0x01){
            System.out.println("success");
            user = tt.getUser();
            clientTrans.setCurrentUser(user);
        }else if(tt.getState()==0x00){
            System.out.println(tt.getInfo());
        }
    }
}
