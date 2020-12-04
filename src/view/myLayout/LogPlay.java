package view.myLayout;

import controller.LogPlayController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
 * 待优化
 *
 * @author Travis
 */
public class LogPlay extends VBox {
    private Main main;
    private final LogPlayController logPlayController;

    public LogPlay(Main main, Stage stage) {
        super(5);
        setPadding(new Insets(25, 25, 25, 25));
        setAlignment(Pos.CENTER);

        stage.setWidth(600);
        stage.setHeight(400);

        logPlayController = new LogPlayController(this);
        logPlayController.setMain(main);
        logPlayController.setStage(stage);

        setAlignment(Pos.CENTER);
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setStyle("");
        logPlayController.setDatePicker(datePicker);

        VBox playerVBox = new VBox();
        Button addPlayer = new Button("Add Player");// 增加玩家

        HBox hBox = new HBox();

        Label label = new Label("Player");
        label.setId("Title");
        TextField textField = new TextField(main.getUser().getUserName());
        textField.setPadding(new Insets(10, 0, 0, 0));
        textField.setId("fieldPlayer");

        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(label, textField);

        playerVBox.getChildren().add(hBox);
        addPlayer.setOnAction(e -> logPlayController.addPlayerButtonOnClicked(playerVBox));

        Button applyButton = new Button("Confirm");
        HBox buttonBox = new HBox(10);
        buttonBox.setSpacing(25);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.getChildren().addAll(applyButton, addPlayer);

        applyButton.setOnAction(e -> logPlayController.log());

        this.getChildren().addAll(datePicker, playerVBox, buttonBox);
    }

    public LogPlayController getLogPlayController() {
        return logPlayController;
    }
}
