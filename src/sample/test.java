package sample;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {


    @Override
    public void start(Stage primaryStage) {

        final Random rng = new Random();
        VBox content = new VBox(5);
        ScrollPane scroller = new ScrollPane(content);
        scroller.setFitToWidth(true);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d);" +
                            "-fx-background-color: -fx-background;",
                    rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
            anchorPane.setStyle(style);
            Label label = new Label("Pane " + (content.getChildren().size() + 1));
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
            Button button = new Button("Remove");
            button.setOnAction(evt -> content.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            content.getChildren().add(anchorPane);
        });

        Scene scene = new Scene(new BorderPane(scroller, null, null, addButton, null), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

