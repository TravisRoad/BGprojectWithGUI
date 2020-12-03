package controller;

import dao.BoardGameDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.TransportThings;
import util.myexception.NoSearchResultException;
import view.Main;
import view.myLayout.BoardBrowserVBox;
import view.myStage.ProgressFrom;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    Main main;
    Stage loadingStage;

    public void setMain(Main main) {
        this.main = main;
    }

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

    public void newStage(int bg_id) {
        loadingStage = new Stage();
        //ProgressFrom progressFrom = new ProgressFrom(loadingStage);
        loadingStage.setScene(new Scene(new AnchorPane(new Label("请稍后")), 400, 500));
        loadingStage.show();
        // progressFrom.activateProgressBar();
        BoardGameFetched boardGameFetched;
        BoardBrowserVBox boardBrowserVBox;

        BoardGameDao boardGameDao = new BoardGameDao();
        boardGameFetched = boardGameDao.fetchBoardGameInfo(bg_id);
        boardBrowserVBox = new BoardBrowserVBox(main, boardGameFetched);

        Scene scene = new Scene(boardBrowserVBox, 1440, 900);
        loadingStage.setScene(scene);
    }

    public void newStage_0(int bg_id) {
        //
    }

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
