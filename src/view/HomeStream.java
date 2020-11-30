package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeStream extends VBox{
    public HomeStream() {
        setLayout();
    }

    private void setLayout() {
        ScrollPane scrollPane = new ScrollPane(this);
        scrollPane.setFitToWidth(true);

        Label title = new Label("Play Now");
        title.setFont(Font.font(null, FontWeight.BOLD, 60));
        title.setPadding(new Insets(50,50,25,50));
        this.getChildren().add(title);

        for (int i = 0;i<10;i++){
            //TODO add retrieved data to entry
            //GameEntry gameEntry = new GameEntry();
        }

        //Button buttonShowMore = new Button("Show More");
    }

}
