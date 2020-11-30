package sample;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transport.ClientTrans;
import view.*;

public class test extends Application {

    private ClientTrans clientTrans;
    @Override
    public void start(Stage primaryStage) {
        ClientTrans ct = new ClientTrans();
        TabPane tabPane = new TabPane();

        Tab tabHome = new Tab("Home", new HomeStream(ct));
        Tab tabSearch = new Tab("Search"  , new SearchMain(ct));
        Tab tabUser = new Tab("User" , new UserMain());

        tabPane.getTabs().add(tabHome);
        tabPane.getTabs().add(tabSearch);
        tabPane.getTabs().add(tabUser);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Board Game Recorder");
        primaryStage.setWidth(1440);
        primaryStage.setHeight(900);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

