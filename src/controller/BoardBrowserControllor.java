package controller;

import dao.BoardGameDao;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.boardgamefetched.BoardGameFetched;
import util.Database;
import util.myexception.NoSearchResultException;
import util.myexception.OtherException;
import view.Main;
import view.myLayout.LogPlay;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 桌游详情浏览页面控制器
 */
public class BoardBrowserControllor implements Initializable {
    public void setMain(Main main) {
        this.main = main;
    }

    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 获得桌游信息
     *
     * @param bg_id 桌游id
     * @return 返回BoardGameDao
     * @throws OtherException          其他异常
     * @throws NoSearchResultException 没有返回结果异常
     */
    private BoardGameFetched fetch(int bg_id) throws OtherException, NoSearchResultException {
        return (new BoardGameDao(new Database())).fetchBoardGameInfo(bg_id);
    }

    /**
     * 打开记录游玩页面
     *
     * @param bg_id 桌游id
     * @param e     事件
     */
    public void openLogPlayStage(int bg_id, Event e) {
        Stage stage = new Stage();
        LogPlay logPlay = new LogPlay(main, stage);
        logPlay.getLogPlayController().setBg_id(bg_id);
        Scene scene = new Scene(new ScrollPane(logPlay), 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 显示自定义警示内容
     *
     * @param state 状态码
     * @param e     事件
     */
    private void showAlert(int state, Event e) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        //设置对话框标题
        alert2.setTitle("提示");
    }
}