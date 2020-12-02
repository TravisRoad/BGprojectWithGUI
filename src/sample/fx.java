package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.boardgamefetched.BoardGameFetched;
import view.myLayout.BoardBrowserVBox;

public class fx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BoardGameFetched b = new BoardGameFetched();
        b.setName("LXY");
        b.setDescription("ajfkasjkfjaskljfkljasklfjkasjklfsk");
        b.setAverageRating(9.0);
        b.setThumbnail("file:src/resource/thumbnail/1.jpg");

        BoardBrowserVBox x = new BoardBrowserVBox(null, b);
        Scene scene = new Scene(x);

        primaryStage.setTitle("hhh");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

