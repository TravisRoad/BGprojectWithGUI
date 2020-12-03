package view.myLayout;

import controller.SearchController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import transport.ClientTrans;
import util.myexception.NoSearchResultException;
import view.Main;
import view.myStage.SearchResultStage;

/**
 * Search界面布局
 */
public class SearchMain extends VBox {
    private SearchController searchController;

    public SearchMain(Main main, Stage stage) {
        searchController = new SearchController();
        searchController.setMain(main);

        setId("MainContainer");//设置为css主容器类

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
                SearchResultStage searchResultStage = new SearchResultStage(stage);
                searchResultStage.setMain(main);
                searchResultStage.setBoardGameModels(searchController.searchBottomOnClick(queryString));
                searchResultStage.build();

                //TODO:rewrite setSearchController
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
