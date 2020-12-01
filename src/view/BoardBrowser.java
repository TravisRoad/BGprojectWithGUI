package view;

import controller.BoardBrowserControllor;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import model.boardgamefetched.BoardGameFetched;
import transport.ClientTrans;
import util.myexception.NoSearchResultException;
import util.myexception.OtherException;

import java.io.IOException;
import java.util.Random;

/**
 * @deprecated
 */
public class BoardBrowser extends Application {
    private MainPage mainPage;
    private BoardBrowserControllor boardBrowserControllor;

    public ScrollPane startBoardBrowser(int bg_id) throws IOException {
        return boardBrowserControllor.getView(bg_id);
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        boardBrowserControllor = new BoardBrowserControllor();
        ScrollPane scroller = startBoardBrowser(174430);
        Scene scene = new Scene(
                new BorderPane(scroller, null, null, null, null),
                400, 400);

        primaryStage.setTitle("hhh");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
