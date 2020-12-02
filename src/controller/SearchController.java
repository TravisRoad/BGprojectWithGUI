package controller;

import dao.BoardGameDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.TransportThings;
import util.myexception.NoSearchResultException;
import view.Main;
import view.myLayout.BoardBrowserVBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    Main main;

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
        BoardGameDao boardGameDao = new BoardGameDao();
        BoardGameFetched boardGameFetched = boardGameDao.fetchBoardGameInfo(bg_id);
        BoardBrowserVBox boardBrowserVBox = new BoardBrowserVBox(main, boardGameFetched);

        Stage stage = new Stage();
        stage.setScene(new Scene(boardBrowserVBox));
        stage.show();
    }
}
