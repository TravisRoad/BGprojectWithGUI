package controller;

import dao.BoardGameDao;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.TransportThings;
import view.Main;
import view.myLayout.BoardBrowserVBox;
import view.myLayout.GameEntry;
import view.myLayout.GameEntryInUser;

import java.util.ArrayList;

public class UserController {
    Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public VBox refreshList(){
        //向服务器查询内容
        TransportThings tt = new TransportThings();
        tt.setQuery("recent");
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
            HBox hBox = new GameEntryInUser(url, name, intro);
            hBox.setOnMouseClicked(e -> {
                this.newStage((int) boardGameModel.getBg_id());
            });
            vvBox.getChildren().add(hBox);
        }
        return vvBox;
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
}
