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


public class GameEntry extends HBox {
    String imageURL;
    String title;
    String intro;
    double rating;


    public GameEntry(String imageURL, String title, String intro, double rating) {
        super();
        this.imageURL = imageURL;
        this.title = title;
        this.intro = intro;
        this.rating = rating;
        setLayout();
    }

    private void setLayout() {

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

        Label ratingLabel = new Label(String.format("%.1f", rating));
        ratingLabel.setFont(Font.font(null, FontWeight.BOLD, 40));
        ratingLabel.setMinWidth(90);
        //TODO: add background graphic to rating

        this.getChildren().addAll(imageView, vBox, ratingLabel);
        setHgrow(imageView, Priority.ALWAYS);
        setHgrow(vBox, Priority.ALWAYS);
        setHgrow(ratingLabel, Priority.ALWAYS);
        setPadding(new Insets(25,50,25,50));
        setSpacing(25);
    }
}
