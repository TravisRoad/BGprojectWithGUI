package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import view.LoginPane;


public class MainController implements Initializable {

    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    // When user click on myButton
    // this method will be called.
    public void open_login_window(ActionEvent event) throws IOException {
        LoginPane lp = new LoginPane();
        lp.startLoginPane();
    }

}
//更多请阅读：https://www.yiibai.com/javafx/javafx-tutorial-for-beginners.html

