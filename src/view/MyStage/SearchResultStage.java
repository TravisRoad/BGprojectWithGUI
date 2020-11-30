package view.MyStage;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import model.BoardGameModel;
import view.GameEntry;
import view.MainPage;

import java.util.ArrayList;

public class SearchResultStage extends Stage {
    ArrayList<BoardGameModel> boardGameModels;
    MainPage mainPage;


    public SearchResultStage(ArrayList<BoardGameModel> boardGameModels, MainPage mainPage) {
        super();
        this.boardGameModels = boardGameModels;
        this.mainPage = mainPage;
        build();
    }

    private void build() {
        VBox vBox = new VBox();
        Parent root = new ScrollPane(vBox);
        for (BoardGameModel boardGameModel : boardGameModels) {
            String url = "../../resource/thumbnail/" + boardGameModel.getBg_id() + ".jpg";
            String title = boardGameModel.getName();
            String intro = boardGameModel.getIntroduction();
            Double rating = boardGameModel.getRate();
            HBox hBox = new GameEntry(url, title, intro, rating);
            vBox.getChildren().add(hBox);
        }

        JMetro jMetro = new JMetro(Style.LIGHT);
        Scene scene = new Scene(root);
        jMetro.setScene(scene);
        setScene(scene);
    }
}