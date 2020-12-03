package view.myLayout;

import controller.LogPlayController;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import view.Main;

/**
 * LogPlay界面布局
 */
public class LogPlay extends VBox {
    private Main main;
    private LogPlayController logPlayController;

    public LogPlay(Main main) {
        logPlayController = new LogPlayController(this);
        logPlayController.setMain(main);

        DatePicker datePicker = new DatePicker();

        VBox playerVBox = new VBox();
        Button addPlayer = new Button("增加玩家");// 增加玩家
        playerVBox.getChildren().add(addPlayer);

        addPlayer.setOnAction(e -> {
            logPlayController.addPlayerButtonOnClicked(playerVBox);
        });

        Button applyButton = new Button("确认");

        applyButton.setOnAction(e -> {
            //logPlayController.log();
        });

        this.getChildren().addAll(datePicker, playerVBox, applyButton);
    }

    public LogPlayController getLogPlayController() {
        return logPlayController;
    }
}
