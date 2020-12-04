package view.myLayout;

import controller.SearchController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BoardGameModel;
import util.TransportThings;
import view.Main;

import java.util.ArrayList;

/**
 * Home界面布局
 *
 * @author Ridd
 */
public class HomeStream extends VBox {
    private final Main main;
    private final SearchController searchController = new SearchController();

    public HomeStream(Main main, Stage stage) {
        this.main = main;
        searchController.setMain(main);
        setLayout();
    }

    /**
     * 构造主页瀑布流
     */
    private void setLayout() {
        setId("MainContainer");//设置为css主容器类
        //基础框架scrollpane
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);
        this.getChildren().add(scrollPane);

        //页面标题
        Label title = new Label("Play Now");
        title.setId("Title-big");
        title.setPadding(new Insets(0, 0, 25, 0));
        vBox.setPadding(new Insets(40, 50, 100, 75));
        vBox.getChildren().add(title);

        //向服务器查询内容
        TransportThings tt = new TransportThings();
        tt.setQuery("top10");
        main.getClientTrans().writeObj(tt);
        tt = (TransportThings) main.getClientTrans().readObj();
        ArrayList<BoardGameModel> boardGameModels = tt.getBoardGameModels();

        //根据服务器返回结果生成瀑布流
        VBox vvBox = new VBox();
        for (BoardGameModel boardGameModel : boardGameModels) {
            String url = "file:src/resource/thumbnail/" + boardGameModel.getBg_id() + ".jpg";
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
    }
}
