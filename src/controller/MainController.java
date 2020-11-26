package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        System.out.println("Button Clicked!");

        AnchorPane root = FXMLLoader.load(getClass().getResource("../resource/Login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Hello World");
        loginStage.setScene(new Scene(root, 300, 275));
        loginStage.show();

    }

}
//更多请阅读：https://www.yiibai.com/javafx/javafx-tutorial-for-beginners.html

