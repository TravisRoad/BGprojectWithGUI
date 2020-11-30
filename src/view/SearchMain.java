package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SearchMain extends VBox {
    public SearchMain(){
        TextField fieldSearch = new TextField();
        Button buttonSearch = new Button("Search!");
        //fieldSearch.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        //fieldSearch.setPadding(new Insets(50,50,50,50));
        //buttonSearch.setPadding(new Insets(50,50,50,50));
        fieldSearch.setFont(Font.font(null, 24));
        buttonSearch.setFont(Font.font(null, 24));
        buttonSearch.setOnAction(buttonSearchClicked -> {
            String queryString = fieldSearch.getText();
            //TODO: perform query
        });
        setSpacing(50);
        setPadding(new Insets(200,50,200,50));
        this.getChildren().addAll(fieldSearch,buttonSearch);
    }


}
