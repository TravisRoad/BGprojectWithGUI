package view.myLayout;

import controller.LogPlayController;
import javafx.scene.layout.VBox;
import view.Main;

public class LogPlay extends VBox {
    private Main main;
    private LogPlayController logPlayController;

    public LogPlay(Main main) {
        logPlayController = new LogPlayController();
        logPlayController.setMain(main);
    }
}
