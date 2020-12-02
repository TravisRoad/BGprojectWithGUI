package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.myLayout.MainTabLayout;

public class Home {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void start() {
        Stage primaryStage = new Stage();
        MainTabLayout mainTabLayout = new MainTabLayout(main);
        Scene mainScene = new Scene(mainTabLayout);

        primaryStage.setTitle("boardgame");
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(1400);
        primaryStage.setHeight(900);
        primaryStage.show();
    }
}
