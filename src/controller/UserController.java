package controller;

import dao.UserDao;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController extends ParentController implements Initializable {

    public boolean login(String userName, String passwd){
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
