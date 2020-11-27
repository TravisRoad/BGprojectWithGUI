package view;

import controller.LoginControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import transport.ClientTrans;
import util.Database;

import javax.xml.crypto.Data;
import java.io.IOException;

public class LoginPane{

    public void startLoginPane(ClientTrans clientTrans) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resource/Login.fxml"));
        AnchorPane root = fxmlLoader.load();

        LoginControl loginControl = fxmlLoader.getController();
        loginControl.setClientTrans(clientTrans);
        Stage loginStage = new Stage();
        loginStage.setTitle("Hello World");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }
}
