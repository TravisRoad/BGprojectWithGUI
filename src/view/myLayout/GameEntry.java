package view.myLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * 瀑布流中的桌游条目
 */
public class GameEntry extends HBox {
    protected String imageURL;
    protected String title;
    protected String intro;
    protected double rating;

    public GameEntry(String imageURL, String title, String intro, double rating) {
        super();
        this.imageURL = imageURL;
        this.title = title;
        this.intro = intro;
        this.rating = rating;
        setLayout();//设置图片和介绍的layout
        setRating();//拆分rating部分的layout，便于setLayout函数在子类中复用
    }

    public GameEntry() {
        super();
    }

    protected void setLayout() {
        //图片容器，固定宽高
        HBox imageBox = new HBox();
        imageBox.setMinWidth(155);
        imageBox.setMaxWidth(155);

        Image image = new Image(imageURL);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(100);
        imageBox.getChildren().add(imageView);

        //标题及简介容器，通过css控制样式
        VBox vBox = new VBox();
        Label titleLabel = new Label(title);
        Label introLabel = new Label(intro);
        titleLabel.setId("Title");
        introLabel.setId("Description");
        introLabel.setPadding(new Insets(10, 0, 0, 0));


        vBox.getChildren().addAll(titleLabel, introLabel);
        this.getChildren().addAll(imageBox, vBox);
        setHgrow(imageView, Priority.ALWAYS);
        setHgrow(vBox, Priority.ALWAYS);

        setPadding(new Insets(25, 0, 25, 0));
        setSpacing(25);
    }

    //拆分rating部分的layout，便于setLayout函数在子类中复用
    protected void setRating() {
        Label ratingLabel = new Label("★" + String.format("%.1f", rating));
        ratingLabel.setId("Title");
        ratingLabel.setMinWidth(80);
        ratingLabel.setPadding(new Insets(10, 0, 0, 0));
        ratingLabel.setAlignment(Pos.CENTER);
        //TODO: add background graphic to rating
        getChildren().add(ratingLabel);
        setHgrow(ratingLabel, Priority.ALWAYS);
    }


}
