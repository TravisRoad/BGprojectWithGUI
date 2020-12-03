package controller;

import dao.BoardGameDao;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.Database;
import util.TransportThings;
import util.myexception.NoSearchResultException;
import util.myexception.OtherException;
import view.Main;
import view.myLayout.LogPlay;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 */
public class BoardBrowserControllor implements Initializable {
    public void setMain(Main main) {
        this.main = main;
    }

    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private BoardGameFetched fetch(int bg_id) throws OtherException, NoSearchResultException {
        return (new BoardGameDao(new Database())).fetchBoardGameInfo(bg_id);
    }

    public void openLogPlayStage(int bg_id, Event e) {
        Stage stage = new Stage();
        LogPlay logPlay = new LogPlay(main, stage);
        logPlay.getLogPlayController().setBg_id(bg_id);
        Scene scene = new Scene(new ScrollPane(logPlay), 400, 600);
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        scene.getStylesheets().add(getClass().getResource("../view/DarkTheme.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        // show new stage

    }

    private void showAlert(int state, Event e) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        //设置对话框标题
        alert2.setTitle("提示");
    }

//    private BoardGameFetched fetch(int bg_id) throws OtherException, NoSearchResultException {
//        TransportThings tt = new TransportThings();
//        BoardGameModel boardGameModel = new BoardGameModel();
//        boardGameModel.setBg_id(bg_id);
//        tt.setQuery("fetch");
//        tt.setBoardGame(boardGameModel);
//        mainPage.getClientTrans().writeObj(tt);//
//        tt = (TransportThings) mainPage.getClientTrans().readObj();
//        if(tt.getState() == 0x01)
//            return tt.getBoardGameFetched();
//        else{
//            switch(tt.getInfo()){
//                case "None":
//                    throw new NoSearchResultException();
//                default:
//                    throw new OtherException();
//            }
//        }
//    }
}
