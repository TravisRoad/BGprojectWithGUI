package view.myLayout;

import controller.SearchController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.BoardGameModel;
import transport.ClientTrans;
import util.TransportThings;
import view.Main;
import view.myStage.ProgressFrom;

import java.util.ArrayList;


/**
 * Home界面布局
 */
public class HomeStream extends VBox {
    private Main main;
    private Stage stage;
    private SearchController searchController = new SearchController();

    public HomeStream(Main main, Stage stage) {
        this.main = main;
        this.stage = stage;
        setLayout();
    }

    private void setLayout() {
        //基础框架scrollpane
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);
        this.getChildren().add(scrollPane);

        //页面标题
        Label title = new Label("Play Now");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        title.setPadding(new Insets(50, 50, 25, 50));
        vBox.getChildren().add(title);

        //向服务器查询内容
        TransportThings tt = new TransportThings();
        tt.setQuery("top10");
        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();
        ArrayList<BoardGameModel> boardGameModels = tt.getBoardGameModels();

        //生成瀑布流
        VBox vvBox = new VBox();
        for (BoardGameModel boardGameModel : boardGameModels) {
            String url = "file:src/resource/thumbnail/" + boardGameModel.getBg_id() + ".jpg";
            //String url = "file:src/resource/avatar.png";
            String name = boardGameModel.getName();
            String intro = boardGameModel.getIntroduction();
            Double rating = boardGameModel.getRate();
            HBox hBox = new GameEntry(url, name, intro, rating);
            hBox.setOnMouseClicked(e -> {
                searchController.newStage((int) boardGameModel.getBg_id());
                searchController.newStage_0((int) boardGameModel.getBg_id());
            });
            vvBox.getChildren().add(hBox);
        }
        vBox.getChildren().add(vvBox);
        //Button buttonShowMore = new Button("Show More");
    }
}
