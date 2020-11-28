package view;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import transport.ClientTrans;

import java.io.IOException;

/**
 * 登陆面板view类
 */
public class LoginPane{
    private MainPage mainPage;

    public void startLoginPane(ClientTrans clientTrans) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resource/Login.fxml"));
        AnchorPane root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setClientTrans(clientTrans);
        loginController.setMainPage(mainPage);
        Stage loginStage = new Stage();
        loginStage.setTitle("Hello World");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
}
