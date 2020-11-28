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
import model.User;
import transport.ClientTrans;

public class MainPage extends Application {

    private ClientTrans clientTrans;
    private User currentUser;
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setStage(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resource/Home.fxml"));
        Parent root = fxmlLoader.load();
        //主窗口关闭则直接退出所有窗口
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });

        connect2server();
        MainController mainController = fxmlLoader.getController(); //获取控制器对象，以传递参数
        mainController.setClientTrans(clientTrans);
        mainController.setMainPage(this);

        primaryStage.setTitle("main");
        primaryStage.setScene(new Scene(root));
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

    public Stage getStage(){
        return this.mainStage;
    }

    public void setStage(Stage stage){
        this.mainStage = stage;
    }
}
