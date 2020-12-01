package view;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

/**
 * 登陆面板view类
 */
public class LoginPane extends VBox {
    private LoginController loginController;

    public LoginPane() {
        //loginController = new LoginController();

        setAlignment(Pos.CENTER);
        setPadding(new Insets(50, 50, 50, 50));

        Label headerLogin = new Label("Login");
        headerLogin.setAlignment(Pos.CENTER_LEFT);
        headerLogin.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

        GridPane gpLogin = new GridPane();
        gpLogin.setAlignment(Pos.CENTER_LEFT);


        Label labelUser = new Label("Username");
        Label labelPass = new Label("Password");
        TextField fieldUser = new TextField();
        PasswordField fieldPass = new PasswordField();

        gpLogin.add(labelUser, 0, 0, 1, 1);
        gpLogin.add(labelPass, 0, 1, 1, 1);
        gpLogin.add(fieldUser, 1, 0, 1, 1);
        gpLogin.add(fieldPass, 1, 1, 1, 1);

        HBox boxButtons = new HBox();
        boxButtons.setAlignment(Pos.CENTER);
        Button buttonSignup = new Button("Sign Up");
        Button buttonLogin = new Button("Login");
        buttonSignup.setFont(Font.font("Calibri", FontWeight.BOLD, 32));
        buttonLogin.setFont(Font.font("Calibri", FontWeight.BOLD, 32));

        boxButtons.getChildren().addAll(buttonSignup, buttonLogin);

        getChildren().addAll(headerLogin, gpLogin, boxButtons);


    }

    public LoginController getLoginController() {
        return loginController;
    }
}
