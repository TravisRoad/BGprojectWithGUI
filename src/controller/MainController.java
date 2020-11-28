package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import transport.ClientTrans;
import util.Database;
import view.LoginPane;


public class MainController extends ParentController implements Initializable{

    @FXML
    private Button loginButton;
    private String str = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    public void open_login_window(ActionEvent event) throws IOException {
        LoginPane lp = new LoginPane();
        lp.startLoginPane(clientTrans);
        lp.setMainPage(mainPage);

    }

    public void setStr(String str) {
        this.str = str;
    }
}

