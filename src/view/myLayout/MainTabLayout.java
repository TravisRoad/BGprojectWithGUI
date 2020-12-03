package view.myLayout;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transport.ClientTrans;
import view.Main;
import view.myLayout.HomeStream;
import view.myLayout.SearchMain;
import view.myLayout.UserMain;

public class MainTabLayout extends VBox {
    private Main main;
    private Stage stage;

    public MainTabLayout(Main main, Stage stage) {
        TabPane tabPane = new TabPane();
        //设置标签不可关闭
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.stage = stage;
        Tab tabHome = new Tab("Home", new HomeStream(main, stage));
        Tab tabSearch = new Tab("Search", new SearchMain(main, stage));
        Tab tabUser = new Tab("User", new UserMain(main));

        tabPane.getTabs().add(tabHome);
        tabPane.getTabs().add(tabSearch);
        tabPane.getTabs().add(tabUser);

        VBox vBox = new VBox(tabPane);
        //Scene scene = new Scene(vBox);
        getChildren().addAll(vBox);
    }


}
