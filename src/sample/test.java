package sample;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transport.ClientTrans;
import view.GameEntry;
import view.HomeStream;
import view.SearchMain;

public class test extends Application {

    private ClientTrans clientTrans;
    @Override
    public void start(Stage primaryStage) {
        clientTrans = new ClientTrans();
        clientTrans.connect();//connect to server

        final Random rng = new Random();
        VBox content = new VBox(5);
        ScrollPane scroller = new ScrollPane(content);
        scroller.setFitToWidth(true);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String url = "https://media.st.dl.pinyuncloud.com/steam/apps/803330/capsule_616x353.jpg?t=1606513853";
            HBox hBox = new GameEntry(url, "Some Board Game", "This is an intro.This is an intro.This is an intro.This is an intro.This is an intro.", 10.0);
            content.getChildren().add(hBox);
        });

        SearchMain a = new SearchMain(clientTrans);
        content.getChildren().add(a);

        HomeStream homeStream = new HomeStream(clientTrans);
        content.getChildren().add(homeStream);

        Scene scene = new Scene(new BorderPane(scroller, null, null, addButton, null), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

