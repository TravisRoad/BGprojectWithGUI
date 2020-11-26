package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPane{

    public void startLoginPane() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("../resource/Login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Hello World");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }
}
