package sample;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class fx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                new BorderPane(new Button(), null, null, null, null),
                400, 400);

        primaryStage.setTitle("hhh");
        primaryStage.setScene(scene);
        primaryStage.show();
//        // TODO Auto-generated method stub
//        Button button = new Button();               //创建按钮
//        button.setText("Say'Hello World!!!'");        //给按钮命名
//        button.setOnAction(new EventHandler<ActionEvent>() {        //给按钮连接事件  注意写法（重点）
//
//            @Override
//            public void handle(ActionEvent event) {
//                // TODO Auto-generated method stub
//                System.out.println("我就不说hello world！");
//            }
//        });
//
//        StackPane root = new StackPane();    //堆栈面板布局  注意stackPane
//        root.getChildren().add(button);        //把按钮加在面板上
//        Scene scene = new Scene(root, 300, 250);    //设定大小
//
//        primaryStage.setTitle("你好");        //设定展示的场景舞台
//        primaryStage.setScene(scene);        //设定场景的大小
//        primaryStage.show();            //舞台展示
    }

    public static void main(String[] args) {
        launch(args);
    }
}

