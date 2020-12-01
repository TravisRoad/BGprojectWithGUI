package view.myStage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LoginPane;
import view.myLayout.MainTabLayout;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene loginScene = new Scene(new LoginPane());
        //Scene mainScene = new Scene(new MainTabLayout());

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Board Game Recorder");
        primaryStage.setWidth(1440);
        primaryStage.setHeight(900);

        primaryStage.show();
    }
}
