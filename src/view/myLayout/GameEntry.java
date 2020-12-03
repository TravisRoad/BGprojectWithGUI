package view.myLayout;

import javafx.geometry.Insets;
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
        setLayout();
        setRating();
    }

    public GameEntry() {
        super();
    }

    protected void setLayout() {
        Image image = new Image(imageURL);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(100);

        VBox vBox = new VBox();
        Label titleLabel = new Label(title);
        Label introLabel = new Label(intro);
        //introLabel.setWrapText(true);

        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 40));
        introLabel.setFont(Font.font(null, 20));


        vBox.getChildren().addAll(titleLabel, introLabel);
        this.getChildren().addAll(imageView, vBox);
        setHgrow(imageView, Priority.ALWAYS);
        setHgrow(vBox, Priority.ALWAYS);

        setPadding(new Insets(25, 50, 25, 50));
        setSpacing(25);
    }

    protected void setRating() {
        Label ratingLabel = new Label(String.format("%.1f", rating));
        ratingLabel.setFont(Font.font(null, FontWeight.BOLD, 40));
        ratingLabel.setMinWidth(90);
        //TODO: add background graphic to rating
        getChildren().add(ratingLabel);
        setHgrow(ratingLabel, Priority.ALWAYS);
    }


}
