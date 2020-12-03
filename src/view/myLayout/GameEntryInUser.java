package view.myLayout;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameEntryInUser extends GameEntry {

    public GameEntryInUser(String imageURL, String title, String intro) {
        super();
        this.imageURL = imageURL;
        this.title = title;
        this.intro = intro;
        setLayout();
        setLastPlayed();

    }

    private void setLastPlayed() {

    }


}
