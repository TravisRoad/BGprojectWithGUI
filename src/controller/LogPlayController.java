package controller;

import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GameLog;
import util.TransportThings;
import view.Main;
import view.myLayout.LogPlay;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class LogPlayController {
    private Main main;
    private ArrayList<TextField> textFields = new ArrayList<>();
    private DatePicker datePicker;
    private Stage stage;
    private LogPlay logPlayVBox;

    private int bg_id;
    private int playerNum;

    public LogPlayController(LogPlay logPlay) {
        this.logPlayVBox = logPlay;
        playerNum = 0;
    }

    public void setBg_id(int bg_id) {
        this.bg_id = bg_id;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void log() {
        ArrayList<String> userNames = new ArrayList<>();
        for (TextField t : textFields) {
            userNames.add(t.getText());
        }
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        GameLog gameLog = new GameLog();
        gameLog.setDate(date);
        gameLog.setUserNames(userNames);
        gameLog.setTheVictoryOne("anyone");
        gameLog.dateFormat();// 格式化

        TransportThings tt = new TransportThings();
        tt.setQuery("gamelog");
        tt.setGameLog(gameLog);
        main.getClientTrans().writeObj(tt); //发送包
        tt = (TransportThings) main.getClientTrans().readObj();
        showAlert(tt.getState());
        //TODO: 返回提示
    }


    public void setTextFields(ArrayList<TextField> textFields) {
        this.textFields = textFields;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    private void showAlert(int state) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("");
        if (state == 0x01)
            alert2.setHeaderText("记录成功");
        else
            alert2.setHeaderText("失败");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
    }

    public void addPlayerButtonOnClicked(VBox playerVBox) {
        HBox hBox = new HBox();

        Label label = new Label("玩家");
        TextField textField = new TextField();
        textFields.add(textField);

        Button removeButton = new Button("移除");

        hBox.getChildren().addAll(label, textField, removeButton);
        playerVBox.getChildren().add(hBox);

        // 绑定移除事件
        removeButton.setOnAction(e -> {
            playerVBox.getChildren().remove(hBox);
            textFields.remove(textField);
            playerNum--;
        });
    }
}
