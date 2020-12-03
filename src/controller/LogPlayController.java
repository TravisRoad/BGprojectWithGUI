package controller;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameLog;
import util.TransportThings;
import view.Main;
import view.myLayout.LogPlay;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LogPlayController(LogPlay logPlay) {
        this.logPlayVBox = logPlay;
        playerNum = 0;
    }

    public void setBg_id(int bg_id) {
        this.bg_id = bg_id;
    }

    public void setMain(Main main) {
        this.main = main;
        textFields.add(new TextField(main.getUser().getUserName()));
    }

    public void log() {
        ArrayList<String> userNames = new ArrayList<>();
        for (TextField t : textFields) {
            userNames.add(t.getText());
        }
        // 日期转化
        LocalDate localDate = datePicker.getValue();
        if (localDate == null) {
            showError("未填写日期");
        }
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Date currentDate = Calendar.getInstance().getTime();
        if (date.after(currentDate)) {
            showError("填写的是未来的日期");
        }

        GameLog gameLog = new GameLog();
        gameLog.setDate(date);
        gameLog.setBg_id(bg_id);
        gameLog.setUserNames(userNames);
        gameLog.setOrganizer(main.getUser().getUserName());
        gameLog.setTheVictoryOne("anyone");
        gameLog.dateFormat();// 格式化

        TransportThings tt = new TransportThings();
        tt.setQuery("gamelog");
        tt.setGameLog(gameLog);
        tt.setUser(main.getUser());

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

    private void showError(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("警告");
        alert.setContentText(info);
        Optional<ButtonType> result = alert.showAndWait();
    }

    private void showAlert(int state) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("");
        boolean flag = false;
        if (state == 0x01) {
            alert2.setHeaderText("记录成功");
            flag = true;
        } else
            alert2.setHeaderText("失败");
        Optional<ButtonType> result = alert2.showAndWait();
        if (flag) stage.close();

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
