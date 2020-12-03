package controller;

import dao.BoardGameDao;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.TransportThings;
import view.Main;
import view.myLayout.BoardBrowserVBox;
import view.myLayout.GameEntry;

import java.util.ArrayList;

public class UserController {
    Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public VBox refreshList(){
        //向服务器查询内容
        TransportThings tt = new TransportThings();
        tt.setQuery("top10");
        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();
        ArrayList<BoardGameModel> boardGameModels = tt.getBoardGameModels();
        VBox vvBox = new VBox();
        for (BoardGameModel boardGameModel : boardGameModels) {
            String url = "file:src/resource/thumbnail/" + boardGameModel.getBg_id() + ".jpg";
            //String url = "file:src/resource/avatar.png";
            String name = boardGameModel.getName();
            String intro = boardGameModel.getIntroduction();
            Double rating = boardGameModel.getRate();
            HBox hBox = new GameEntry(url, name, intro, rating);
            hBox.setOnMouseClicked(e -> {
                this.newStage((int) boardGameModel.getBg_id());
            });
            vvBox.getChildren().add(hBox);
        }
        return vvBox;
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
