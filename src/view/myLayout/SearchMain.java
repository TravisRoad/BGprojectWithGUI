package view.myLayout;

import controller.SearchController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.myexception.NoSearchResultException;
import view.Main;
import view.myStage.SearchResultStage;

/**
 * Search界面布局
 *
 * @author Ridd
 */
public class SearchMain extends VBox {
    private final SearchController searchController;

    public SearchMain(Main main, Stage stage) {
        searchController = new SearchController();
        searchController.setMain(main);
        setId("MainContainer");//设置为css主容器类

        TextField fieldSearch = new TextField();
        fieldSearch.setId("FieldSearch");
        Button buttonSearch = new Button("Search!");
        buttonSearch.setId("buttonSearch");
        setAlignment(Pos.CENTER);

        //搜索按钮点击逻辑
        buttonSearch.setOnAction(buttonSearchClicked -> {
            String queryString = fieldSearch.getText();
            try {
                SearchResultStage searchResultStage = new SearchResultStage(stage);
                searchResultStage.setMain(main);
                searchResultStage.setBoardGameModels(searchController.searchBottomOnClick(queryString));
                searchResultStage.build();
                searchResultStage.setSearchController(searchController);
                searchResultStage.show();
            } catch (NoSearchResultException e) {
                e.printStackTrace();
            }
        });
        setSpacing(50);
        setPadding(new Insets(0, 150, 150, 150));
        this.getChildren().addAll(fieldSearch, buttonSearch);
    }
}
