package controller;

import dao.BoardGameDao;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.TransportThings;
import util.myexception.NoSearchResultException;
import view.Main;
import view.myLayout.BoardBrowserVBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * 搜索入口界面的控制器
 *
 * @author Ridd
 */
public class SearchController implements Initializable {
    Main main;
    Stage loadingStage;

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 点击搜索按钮，返回一个桌游的ArrayList
     *
     * @param searchStr 搜索字符串
     * @return 返回BoardGameModel的ArrayList
     * @throws NoSearchResultException 无搜索结果异常
     */
    public ArrayList<BoardGameModel> searchBottomOnClick(String searchStr) throws NoSearchResultException {
        ArrayList<BoardGameModel> boardGameModels = null;
        TransportThings tt = new TransportThings();
        tt.setQuery("search");
        tt.setInfo(searchStr);
        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();
        if (tt.getState() == 0x00) {
            throw new NoSearchResultException();
        } else {
            System.out.println("Search success");
            boardGameModels = tt.getBoardGameModels();
        }
        return boardGameModels;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 点击桌游条目后，弹出桌游详情窗口
     *
     * @param bg_id 桌游id
     */
    public void newStage(int bg_id) {
        loadingStage = new Stage();
        loadingStage.setScene(new Scene(new AnchorPane(new Label("请稍后")), 400, 500));
        loadingStage.show();
        BoardGameFetched boardGameFetched;
        BoardBrowserVBox boardBrowserVBox;

        BoardGameDao boardGameDao = new BoardGameDao();
        boardGameFetched = boardGameDao.fetchBoardGameInfo(bg_id);
        boardBrowserVBox = new BoardBrowserVBox(main, boardGameFetched);

        Scene scene = new Scene(boardBrowserVBox, 1440, 900);
        loadingStage.setScene(scene);
    }

    public void newStage_0(int bg_id) {
        //施工中
    }

    /**
     * 多线程支持
     */
    class thread implements Runnable {
        private Stage loadingStage;
        private int bg_id;

        public thread(Stage loginStage, int bg_id) {
            this.loadingStage = loginStage;
            this.bg_id = bg_id;
        }

        @Override
        public void run() {
        }
    }
}
