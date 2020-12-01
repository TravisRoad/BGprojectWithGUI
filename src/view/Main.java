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
        MainTabLayout mainTabLayout = new MainTabLayout();
        Scene mainScene = new Scene(mainTabLayout);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Board Game Recorder");
        primaryStage.setWidth(1440);
        primaryStage.setHeight(900);

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
