package view.myLayout;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.BoardGameModel;
import view.Main;

/**
 * User界面布局
 */
public class UserMain extends SplitPane {
    Main main;
    UserController userController;

    public void setMain(Main main) {
        this.main = main;
    }

    public UserMain(Main main) {
        setId("MainContainer");//设置为css主容器类
        userController = new UserController();
        this.main = main;
        userController.setMain(main);
        setDividerPositions(0.25);

        //split pane左半部分
        VBox leftControl = new VBox();
        leftControl.setAlignment(Pos.CENTER);

        //显示用户信息的grid pane
        GridPane gpBasicInfo = new GridPane();
        gpBasicInfo.setAlignment(Pos.CENTER);
        gpBasicInfo.setHgap(25);
        gpBasicInfo.setVgap(25);

        //TODO: customize user profile
        ImageView imgAvatar = new ImageView();

        Label labelUsername = new Label(main.getUser().getNickName());
        labelUsername.setAlignment(Pos.CENTER);
        labelUsername.setFont(Font.font("Calibri", FontWeight.BOLD, 32));
        userController.setLabelUsername(labelUsername);

        Button buttonEditProfile = new Button("Edit Profile");
        buttonEditProfile.setFont(Font.font("Calibri", 20));
        buttonEditProfile.setOnAction(editProfile -> {
            userController.newEditProfileStage();
        });


        gpBasicInfo.add(imgAvatar, 0, 0, 1, 1);
        gpBasicInfo.add(labelUsername, 0, 1, 1, 1);
        gpBasicInfo.add(buttonEditProfile, 0, 2, 1, 1);
        leftControl.getChildren().addAll(gpBasicInfo);

        //split pane右半部分
        VBox rightBox = new VBox();
        HBox rightTop = new HBox();
        ScrollPane rightControl = new ScrollPane(rightBox);



        Label labelRecent = new Label("Recently Played");
        labelRecent.setFont(Font.font("Calibri", FontWeight.BOLD, 32));
        labelRecent.setAlignment(Pos.CENTER_LEFT);

        Button buttonRefresh = new Button("Refresh");
        buttonRefresh.setAlignment(Pos.CENTER_RIGHT);
        buttonRefresh.setOnAction(e -> {
            rightBox.getChildren().remove(1);
            VBox rightContentNew = userController.refreshList();
            rightBox.getChildren().add(rightContentNew);
        });


        rightTop.getChildren().addAll(labelRecent, buttonRefresh);
        rightBox.getChildren().addAll(rightTop);
        rightBox.setFillWidth(true);
        HBox.setHgrow(buttonRefresh, Priority.ALWAYS);
        HBox.setHgrow(labelRecent, Priority.ALWAYS);
        rightTop.setPadding(new Insets(10, 25, 25, 25));
        rightTop.setSpacing(50);
        rightBox.getChildren().add(userController.refreshList());

        getItems().addAll(leftControl, rightControl);
    }
}
