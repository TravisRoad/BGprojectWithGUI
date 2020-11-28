package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import transport.ClientTrans;
import util.Database;
import view.LoginPane;


public class MainController extends ParentController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button ButtonSearch;
    @FXML
    private TextField FieldSearch;
    @FXML
    private Label LabelUsername;
    @FXML
    private Tab TabHome;
    @FXML
    private Tab TabSearch;
    @FXML
    private Tab TabUser;
    @FXML
    private Tab TabSettings;
    private String str = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    public void open_login_window(ActionEvent event) throws IOException {
        LoginPane lp = new LoginPane();
        lp.setMainPage(mainPage);
        lp.startLoginPane(clientTrans);
        change_scene_user(null);

    }

    public void change_scene_user(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resource/Main.fxml"));
        Parent root = fxmlLoader.load();
        mainPage.getStage().setScene(new Scene(root));
    }

    public void setStr(String str) {
        this.str = str;
    }
}

