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
 * 登陆界面的结构,继承了VBox
 *
 * @author Travis
 */
public class LoginPane extends VBox {
    private LoginController loginController;

    public LoginPane(Main main) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50, 50, 50, 50));

        Label headerLogin = new Label("Login");
        headerLogin.setId("Title");
        headerLogin.setAlignment(Pos.TOP_CENTER);
        headerLogin.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
        headerLogin.setPadding(new Insets(0, 50, 25, 50));

        GridPane gpLogin = new GridPane();
        gpLogin.setAlignment(Pos.CENTER);

        Label labelUser = new Label("Username");
        Label labelPass = new Label("Password");
        TextField fieldUser = new TextField();
        PasswordField fieldPass = new PasswordField();

        gpLogin.add(labelUser, 0, 0, 1, 1);
        gpLogin.add(labelPass, 0, 1, 1, 1);
        gpLogin.add(fieldUser, 1, 0, 1, 1);
        gpLogin.add(fieldPass, 1, 1, 1, 1);
        gpLogin.setHgap(10);
        gpLogin.setVgap(10);

        Button buttonSignup = new Button("Sign Up");
        Button buttonLogin = new Button("Login");
        buttonSignup.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        buttonLogin.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        //buttonLogin.setMinWidth(buttonSignup.getWidth());

        HBox boxButtons = new HBox();
        boxButtons.getChildren().addAll(buttonSignup, buttonLogin);
        boxButtons.setAlignment(Pos.CENTER);
        boxButtons.setPadding(new Insets(25, 25, 25, 25));
        boxButtons.setSpacing(25);
        /*gpLogin.add(buttonSignup, 0, 2);
        gpLogin.add(buttonLogin, 1, 2);
        GridPane.setMargin(buttonLogin, new Insets(0, 1, 0, 50));*/

        // 快速登录
        Button quick = new Button("quickLogin");
        quick.setDefaultButton(true);
        // fixme: 记得删除

        quick.setOnAction(e -> {
            loginController.login("lxy", "123456", e);
        });
        getChildren().addAll(headerLogin, gpLogin, boxButtons, quick);

        loginController = new LoginController(buttonLogin, buttonSignup, fieldUser, fieldPass);
        loginController.setMain(main);
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
