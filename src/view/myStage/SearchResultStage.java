package view.myStage;

import controller.SearchController;
import dao.BoardGameDao;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import model.BoardGameModel;
import view.Main;
import view.myLayout.BoardBrowserVBox;
import view.myLayout.GameEntry;

import java.util.ArrayList;

public class SearchResultStage extends Stage {
    ArrayList<BoardGameModel> boardGameModels;
    Stage stage;
    Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setBoardGameModels(ArrayList<BoardGameModel> boardGameModels) {
        this.boardGameModels = boardGameModels;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    SearchController searchController;

    public SearchResultStage() {
        super();
    }

    public SearchResultStage(Stage stage) {
        super();
        this.stage = stage;
    }

    public void build() {
        VBox vBox = new VBox();
        Parent root = new ScrollPane(vBox);
        for (BoardGameModel boardGameModel : boardGameModels) {
            String url = "file:src/resource/thumbnail/" + boardGameModel.getBg_id() + ".jpg";
            //String url = "file:src/resource/avatar.png";
            String title = boardGameModel.getName();
            String intro = boardGameModel.getIntroduction();
            Double rating = boardGameModel.getRate();
            HBox hBox = new GameEntry(url, title, intro, rating);
            hBox.setOnMouseClicked(e -> {
                searchController.newStage((int) boardGameModel.getBg_id());
                searchController.newStage_0((int) boardGameModel.getBg_id());
            });
            vBox.getChildren().add(hBox);
        }

        Scene scene = new Scene(root);
        setScene(scene);
    }
}
