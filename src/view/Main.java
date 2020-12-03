package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import transport.ClientTrans;
import view.myLayout.MainTabLayout;

/**
 * 入口（login）
 */
public class Main extends Application {

    private User user;
    private ClientTrans clientTrans;

    @Override
    public void start(Stage primaryStage) throws Exception {
        clientTrans = new ClientTrans();
        clientTrans.connect();
        user = new User();



        LoginPane loginPane = new LoginPane(this);
        loginPane.getLoginController().setLoginStage(primaryStage);

        Scene loginScene = new Scene(loginPane);

        //Application.setUserAgentStylesheet(STYLESHEET_MODENA);
        //loginScene.getStylesheets().add(getClass().getResource("DarkTheme.css").toExternalForm());

        Application.setUserAgentStylesheet(getClass().getResource("DarkTheme.css").toExternalForm());

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Board Game Recorder");
        primaryStage.setWidth(600);
        primaryStage.setHeight(800);

        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });



        primaryStage.show();
    }

    public void pub_launch(String[] args) {
        launch(args);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClientTrans getClientTrans() {
        return clientTrans;
    }

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }
}
