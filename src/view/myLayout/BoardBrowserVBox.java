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
        Pane pane = new Pane();
        int rank = boardGameFetched.getRank();

        // 获取各种属性
        String name = boardGameFetched.getName();
        String description = boardGameFetched.getDescription().replace("&#10;", "\r\n");
        Double averageRating = boardGameFetched.getAverageRating();

        // 封面图片，TODO：背景未设置
        Image image = new Image(boardGameFetched.getThumbnail());
        ImageView imageView = new ImageView(image);


        VBox vBoxHeader = new VBox();
        vBoxHeader.setAlignment(Pos.CENTER);

        // 描述部分
        HBox hBoxDescription = new HBox();
        {
            Polygon polygon = new Polygon(); //六边形
            polygon.getPoints().addAll(20.0, 0.0,
                    0.0, 11.6,
                    0.0, 28.4,
                    20.0, 40.0,
                    40.0, 28.4,
                    40.0, 11.6);
            polygon.setFill(Color.GREEN);

            Label rateLabel = new Label(String.format("%.1f", averageRating));
            rateLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
            rateLabel.setTextFill(Color.WHITE);
            rateLabel.setLayoutY(10);
            rateLabel.setLayoutX(3);
            Group g = new Group();
            g.getChildren().addAll(polygon, rateLabel);

            VBox des = new VBox();
            Text nameText = new Text(name);
            nameText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            Label textDescription = new Label(description);
            textDescription.setWrapText(true);
            des.getChildren().addAll(nameText, textDescription);


            hBoxDescription.getChildren().add(g);
            hBoxDescription.getChildren().add(des);
            HBox.setMargin(polygon, new Insets(1.0, 1.0, 1.0, 1.0));
        }
        //描述部分结束

        Button logPlay = new Button("记录");
        logPlay.setOnAction(e -> boardBrowserControllor.openLogPlayStage(boardGameFetched.getGameId(), e));// 绑定记录按钮

        vBoxHeader.getChildren().add(imageView);

        vBoxHeader.getChildren().add(new Text("排名:" + rank + "\t出版年份:" + boardGameFetched.getYearPublished()));
        vBoxHeader.getChildren().add(hBoxDescription);
        vBoxHeader.getChildren().add(logPlay);

        getChildren().add(vBoxHeader);
    }
}
