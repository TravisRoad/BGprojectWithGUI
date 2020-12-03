package controller;

import dao.BoardGameDao;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.GameLog;
import model.User;
import model.boardgamefetched.BoardGameFetched;
import util.TransportThings;
import view.Main;
import view.myLayout.BoardBrowserVBox;
import view.myLayout.GameEntry;
import view.myLayout.GameEntryInUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

public class UserController {
    private Main main;
    private Label labelUsername;

    public void setLabelUsername(Label labelUsername) {
        this.labelUsername = labelUsername;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public VBox refreshList() {
        //向服务器查询内容
        TransportThings tt = new TransportThings();
        tt.setQuery("recent");
        tt.setUser(main.getUser());
        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();
        VBox vBox = new VBox();
        switch (tt.getState()) {
            case 0x00: // 访问失败，输出错误信息
                System.out.println(tt.getInfo());
                break;
            case 0x01:
                ArrayList<BoardGameModel> boardGameModels = tt.getBoardGameModels();
                ArrayList<GameLog> gameLogs = tt.getGameLogs();
                Iterator<GameLog> iterator = gameLogs.iterator();
                for (BoardGameModel boardGameModel : boardGameModels) {
                    String url = "file:src/resource/thumbnail/" + boardGameModel.getBg_id() + ".jpg";
                    String name = boardGameModel.getName();
                    String intro = boardGameModel.getIntroduction();
                    Double rating = boardGameModel.getRate();

                    GameLog gameLog = iterator.next();
                    Date date = gameLog.getDate();
                    ArrayList<String> userNames = gameLog.getUserNames();
                    HBox hBox = new GameEntryInUser(url, name, intro, date, userNames);
                    hBox.setOnMouseClicked(e -> {
                        this.newStage((int) boardGameModel.getBg_id());
                    });
                    vBox.getChildren().add(hBox);
                }
                break;
            default:
                break;
        }
        return vBox;
    }

    public void newStage(int bg_id) {
        Stage loadingStage = new Stage();
        //ProgressFrom progressFrom = new ProgressFrom(loadingStage);
        loadingStage.setScene(new Scene(new AnchorPane(new Label("请稍后")), 400, 500));
        loadingStage.show();
        // progressFrom.activateProgressBar();
        BoardGameFetched boardGameFetched;
        BoardBrowserVBox boardBrowserVBox;

        BoardGameDao boardGameDao = new BoardGameDao();
        boardGameFetched = boardGameDao.fetchBoardGameInfo(bg_id);
        boardBrowserVBox = new BoardBrowserVBox(main, boardGameFetched);

        Scene scene = new Scene(boardBrowserVBox, 400, 500);
        loadingStage.setScene(scene);
    }

    public void newEditProfileStage() {
        Stage stage = new Stage();
        HBox hBox = new HBox(10);
        VBox vBox = new VBox(10);
        Label label = new Label("new nickname");
        TextField textField = new TextField();
        Button buttonApply = new Button("apply");
        Button buttonCancel = new Button("cancel");
        hBox.getChildren().addAll(buttonApply, buttonCancel);
        vBox.getChildren().addAll(label, textField, hBox);

        buttonApply.setOnAction(e -> {
            if (textField.getText().isEmpty() || textField.getText() == null) {
                showAlert("nickname is illegal", 0x00, null);
            } else {
                sendChangeRequest(textField.getText(), stage);
            }
        });
        buttonCancel.setOnAction(e -> {
            stage.close();
        });
        stage.setTitle("change nickname");
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 发送更改昵称请求
     *
     * @param newName
     */
    private void sendChangeRequest(String newName, Stage stage) {
        TransportThings tt = new TransportThings();
        tt.setQuery("changeNickName");
        User user = new User();
        user.setUserID((int) main.getUser().getUserID());
        user.setNickName(newName);
        tt.setUser(user);

        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();

        switch (tt.getState()) {
            case 0x01:
                main.getUser().setNickName(newName);
                labelUsername.setText(newName);
                showAlert("success", 0x01, stage);
                break;
            case 0x00:
                showAlert(tt.getInfo() + "\nPlease try again", 0x00, stage);
                break;
            default:
                break;
        }
    }

    private void showAlert(String str, int state, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.setContentText(str);
        boolean flag = false;
        switch (state) {
            case 0x00:
                break;
            case 0x01:
                flag = true;
                break;
            default:
                break;
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (flag) stage.close();
    }
}
