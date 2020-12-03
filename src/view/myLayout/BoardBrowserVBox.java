package view.myLayout;

import controller.BoardBrowserControllor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.boardgamefetched.BoardGameFetched;
import util.myexception.NoSearchResultException;
import util.myexception.OtherException;
import view.Main;

/**
 * 浏览页面的主要构成对象
 */
public class BoardBrowserVBox extends VBox {
    private BoardBrowserControllor boardBrowserControllor;

    public BoardBrowserVBox() {
        super(5);
    }

    public BoardBrowserVBox(Main main, BoardGameFetched boardGameFetched) {
        super(5);
        boardBrowserControllor = new BoardBrowserControllor();
        boardBrowserControllor.setMain(main);
        build(boardGameFetched);
    }

    private void build(BoardGameFetched boardGameFetched) {
        setAlignment(Pos.CENTER);
        int rank = boardGameFetched.getRank();

        // 获取各种属性
        String name = boardGameFetched.getName();
        String description = boardGameFetched.getDescription().replace("&#10;", "\r\n");
        Double averageRating = boardGameFetched.getAverageRating();

        // 封面图片，TODO：背景未设置
        Image image = new Image(boardGameFetched.getThumbnail());
        ImageView imageView = new ImageView(image);

        VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label(name);
        nameLabel.setId("Title-big");
        Label rateLabel = new Label("★" + String.format("%.1f", averageRating) + "\tRanking: " + rank + "\tPublished: " + boardGameFetched.getYearPublished());
        rateLabel.setId("Title");
        Label descLabel = new Label(description);
        descLabel.setMaxWidth(1000);
        descLabel.setMinHeight(500);
        descLabel.setWrapText(true);
        ScrollPane scrollPane = new ScrollPane(descLabel);
        scrollPane.setMaxWidth(1010);
        mainBox.setPadding(new Insets(50,50,50,50));
        mainBox.setSpacing(25);


        Button logPlay = new Button("LogPlay");
        logPlay.setOnAction(e -> boardBrowserControllor.openLogPlayStage(boardGameFetched.getGameId(), e));// 绑定记录按钮


        mainBox.getChildren().add(imageView);
        mainBox.getChildren().addAll(nameLabel, rateLabel, scrollPane);
        mainBox.getChildren().add(logPlay);

        getChildren().add(mainBox);
    }
}
