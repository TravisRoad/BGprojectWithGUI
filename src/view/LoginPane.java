package view;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import transport.ClientTrans;

import java.awt.*;
import java.io.IOException;

/**
 * 登陆面板view类
 */
public class LoginPane{
    private LoginController loginController;

    public LoginPane() {
        loginController = new LoginController();

        VBox vBox = new VBox();

        GridPane gpLogin = new GridPane();

        TextField userName = new TextField();




    }

    public LoginController getLoginController() {
        return loginController;
    }
}
