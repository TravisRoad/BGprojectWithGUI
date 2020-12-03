package view.myLayout;

import com.browniebytes.javafx.control.DateTimePicker;
import controller.LogPlayController;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Main;

import java.time.LocalDate;

/**
 * LogPlay界面布局
 */
public class LogPlay extends VBox {
    private Main main;
    private LogPlayController logPlayController;

    public LogPlay(Main main, Stage stage) {
        super(5);
        logPlayController = new LogPlayController(this);
        logPlayController.setMain(main);
        logPlayController.setStage(stage);

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setStyle("");
        logPlayController.setDatePicker(datePicker);

        VBox playerVBox = new VBox();
        Button addPlayer = new Button("增加玩家");// 增加玩家

        HBox hBox = new HBox();

        Label label = new Label("玩家");
        TextField textField = new TextField(main.getUser().getUserName());
        hBox.getChildren().addAll(label, textField);

        playerVBox.getChildren().add(hBox);
        addPlayer.setOnAction(e -> {
            logPlayController.addPlayerButtonOnClicked(playerVBox);
        });

        Button applyButton = new Button("确认");
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(applyButton, addPlayer);

        applyButton.setOnAction(e -> {
            logPlayController.log();
        });

        this.getChildren().addAll(datePicker, playerVBox, buttonBox);
    }

    public LogPlayController getLogPlayController() {
        return logPlayController;
    }
}
