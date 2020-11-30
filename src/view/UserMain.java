package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserMain extends SplitPane {
    public UserMain(){
        setDividerPositions(0.25);


        VBox leftControl = new VBox();

        leftControl.setAlignment(Pos.CENTER);

        GridPane gpBasicInfo = new GridPane();
        gpBasicInfo.setAlignment(Pos.CENTER);
        gpBasicInfo.setHgap(25);
        gpBasicInfo.setVgap(25);

        //TODO: customize user profile
        ImageView imgAvatar = new ImageView();

        Label labelUsername = new Label("RiddMa");
        labelUsername.setAlignment(Pos.CENTER);
        labelUsername.setFont(Font.font("Calibri", FontWeight.BOLD, 32));

        Button buttonEditProfile = new Button("Edit Profile");
        buttonEditProfile.setFont(Font.font("Calibri",20));
        buttonEditProfile.setOnAction(editProfile -> {
            //TODO:edit profile
        });


        gpBasicInfo.add(imgAvatar,0,0,1,1);
        gpBasicInfo.add(labelUsername,0,1,1,1);
        gpBasicInfo.add(buttonEditProfile,0,2,1,1);
        leftControl.getChildren().addAll(gpBasicInfo);

        VBox rightControl = new VBox();

        getItems().addAll(leftControl,rightControl);
    }
}
