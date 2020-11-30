package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.BoardGameModel;
import util.TransportThings;
import util.myexception.NoSearchResultException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController extends ParentController implements Initializable {

    public ArrayList<BoardGameModel> searchBottomOnClick(String searchStr) throws NoSearchResultException {
        ArrayList<BoardGameModel> boardGameModels = null;
        TransportThings tt = new TransportThings();
        tt.setQuery("search");
        tt.setInfo(searchStr);
        clientTrans.writeObj(tt);
        tt = (TransportThings) clientTrans.readObj();
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
}
