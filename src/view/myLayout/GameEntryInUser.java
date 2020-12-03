package view.myLayout;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;

public class GameEntryInUser extends GameEntry {

    public GameEntryInUser(String imageURL, String title, String intro, Date playDate, ArrayList<String> userName) {
        super();
        this.imageURL = imageURL;
        this.title = title;
        this.intro = intro;
        setLayout();
        setLastPlayed(playDate, userName);
    }

    private void setLastPlayed(Date playDate, ArrayList<String> userNames) {
        VBox vBox = new VBox(5);
        vBox.getChildren().add(new Label(playDate.toString()));
        for (String userName : userNames) {
            vBox.getChildren().add(new Label(userName));
        }
        this.getChildren().add(vBox);
    }


}
