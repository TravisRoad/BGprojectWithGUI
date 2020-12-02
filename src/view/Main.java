package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import transport.ClientTrans;
import view.myLayout.MainTabLayout;

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
        primaryStage.setScene(new Scene(loginPane));

        primaryStage.setTitle("Board Game Recorder");
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);

        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });

        primaryStage.show();
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
