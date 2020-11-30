package controller;

import dao.BoardGameDao;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.*;
import model.BoardGameModel;
import model.boardgamefetched.BoardGameFetched;
import util.Database;
import util.TransportThings;
import util.myexception.NoSearchResultException;
import util.myexception.OtherException;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardBrowserControllor extends ParentController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ScrollPane getView(int bg_id) {
        VBox vBox = new VBox(5);
        ScrollPane scroller = new ScrollPane(vBox);
        vBox.setAlignment(Pos.CENTER);
        scroller.setFitToWidth(true);
        BoardGameFetched boardGameFetched;
        try {
            boardGameFetched = fetch(bg_id);
            Pane pane = new Pane();
            int rank = boardGameFetched.getRank();
            String name = boardGameFetched.getName();
            String description = boardGameFetched.getDescription().replace("&#10;&#10;", "\r\n");
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
            logPlay.setOnAction(e -> openLogPlayStage());

            vBoxHeader.getChildren().add(imageView);

            vBoxHeader.getChildren().add(new Text("排名:" + rank + "\t出版年份:" + boardGameFetched.getYearPublished()));
            vBoxHeader.getChildren().add(hBoxDescription);
            vBoxHeader.getChildren().add(logPlay);

            vBox.getChildren().add(vBoxHeader);


        } catch (OtherException e) {
            e.printStackTrace();//TODO
        } catch (NoSearchResultException e) {
            e.printStackTrace();//TODO
        }


        return scroller;
    }

    private BoardGameFetched fetch(int bg_id) throws OtherException, NoSearchResultException {
        return (new BoardGameDao(new Database())).fetchBoardGameInfo(bg_id);
    }

    private void openLogPlayStage() {

    }
//    private BoardGameFetched fetch(int bg_id) throws OtherException, NoSearchResultException {
//        TransportThings tt = new TransportThings();
//        BoardGameModel boardGameModel = new BoardGameModel();
//        boardGameModel.setBg_id(bg_id);
//        tt.setQuery("fetch");
//        tt.setBoardGame(boardGameModel);
//        mainPage.getClientTrans().writeObj(tt);// TODO:对称的server处未处理
//        tt = (TransportThings) mainPage.getClientTrans().readObj();
//        if(tt.getState() == 0x01)
//            return tt.getBoardGameFetched();
//        else{
//            switch(tt.getInfo()){
//                case "None":
//                    throw new NoSearchResultException();
//                default:
//                    throw new OtherException();
//            }
//        }
//    }
}
