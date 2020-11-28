package view;

import controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import model.User;
import transport.ClientTrans;

public class MainPage extends Application {

    private ClientTrans clientTrans;
    private User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resource/Main.fxml"));
        Parent root = fxmlLoader.load();
        //主窗口关闭则直接退出所有窗口
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });

        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        connect2server();
        MainController mainController = fxmlLoader.getController(); //获取控制器对象，以传递参数
        mainController.setClientTrans(clientTrans);
        mainController.setMainPage(this);

        primaryStage.setTitle("main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }

    public ClientTrans getClientTrans() {
        return clientTrans;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean connect2server() {
        clientTrans = new ClientTrans();
        return clientTrans.connect();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
