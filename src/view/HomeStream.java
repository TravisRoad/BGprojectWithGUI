package view;

import controller.SearchController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import transport.ClientTrans;
import util.myexception.NoSearchResultException;
import view.MyStage.SearchResultStage;

public class HomeStream extends VBox {
    private ClientTrans clientTrans;
    private SearchController searchController;

    public HomeStream(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
        setLayout();
    }

    private void setLayout() {
        ScrollPane scrollPane = new ScrollPane(this);
        scrollPane.setFitToWidth(true);

        Label title = new Label("Play Now");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
        title.setPadding(new Insets(50, 50, 25, 50));
        this.getChildren().add(title);

        for (int i = 0; i < 10; i++) {
            //TODO:add retrieved data to entry
            /*SearchController searchController = new SearchController();
            searchController.setClientTrans(clientTrans);
            this.clientTrans = clientTrans;
            try {
                Stage stage = new SearchResultStage(searchController.searchBottomOnClick());
                stage.show();
            } catch (NoSearchResultException e) {
                e.printStackTrace();
                //TODO:弹出错误窗口
            }
            GameEntry gameEntry = new GameEntry();*/
        }

        //Button buttonShowMore = new Button("Show More");
    }

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }
}
