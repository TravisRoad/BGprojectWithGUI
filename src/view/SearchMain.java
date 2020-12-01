package view;

import controller.SearchController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import transport.ClientTrans;
import util.myexception.NoSearchResultException;
import view.MyStage.SearchResultStage;

public class SearchMain extends VBox {
    private ClientTrans clientTrans;
    private SearchController searchController;

    public SearchMain(ClientTrans clientTrans) {
        searchController = new SearchController();
        searchController.setClientTrans(clientTrans);
        this.clientTrans = clientTrans;


        TextField fieldSearch = new TextField();
        Button buttonSearch = new Button("Search!");
        //fieldSearch.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        //fieldSearch.setPadding(new Insets(50,50,50,50));
        //buttonSearch.setPadding(new Insets(50,50,50,50));
        fieldSearch.setFont(Font.font("Calibri", 24));
        buttonSearch.setFont(Font.font("Calibri", 24));
        buttonSearch.setOnAction(buttonSearchClicked -> {
            String queryString = fieldSearch.getText();
            try {
                SearchResultStage searchResultStage = new SearchResultStage(searchController.searchBottomOnClick(queryString));
                searchResultStage.setSearchController(searchController);
                searchResultStage.show();
            } catch (NoSearchResultException e) {
                e.printStackTrace();
                //TODO:弹出错误窗口
            }
        });
        setSpacing(50);
        setPadding(new Insets(200,50,200,50));
        this.getChildren().addAll(fieldSearch,buttonSearch);
    }
}
