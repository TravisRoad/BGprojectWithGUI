package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.myLayout.MainTabLayout;

/**
 * 主界面的View对象
 */
public class Home {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void start() {
        Stage primaryStage = new Stage();
        MainTabLayout mainTabLayout = new MainTabLayout(main, primaryStage);
        Scene mainScene = new Scene(mainTabLayout);

        primaryStage.setTitle("BoardGame Recorder");
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(1400);
        primaryStage.setHeight(900);
        primaryStage.show();
    }
}
