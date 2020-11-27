package view;

import controller.LoginControl;
import controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import transport.ClientTrans;

public class Client extends Application {

    private ClientTrans clientTrans;

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

        MainController mainController = fxmlLoader.getController(); //获取控制器对象，以传递参数
        mainController.setClientTrans(clientTrans);

        connect2server();  // TODO:连接到服务器的返回值未处理
        primaryStage.setTitle("main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private boolean connect2server() {
        clientTrans = new ClientTrans();
        return clientTrans.connect();
    }
}
