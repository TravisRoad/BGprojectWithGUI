package controller;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import util.GameLog;
import util.TransportThings;
import view.Main;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class LogPlayController {
    private Main main;
    private ArrayList<TextField> textFields;
    private DatePicker datePicker;
    private Stage stage;

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

        TransportThings tt = new TransportThings();
        tt.setQuery("gamelog");
        tt.setGameLog(gameLog);
        main.getClientTrans().writeObj(tt);
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


}
