package view.myLayout;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Main;

/**
 * 主界面标签页布局
 */
public class MainTabLayout extends VBox {
    private Main main;

    public MainTabLayout(Main main, Stage stage) {
        TabPane tabPane = new TabPane();
        //设置标签不可关闭
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //调用各自的构造方法构造标签页
        Tab tabHome = new Tab("Home", new HomeStream(main, stage));
        Tab tabSearch = new Tab("Search", new SearchMain(main, stage));
        Tab tabUser = new Tab("User", new UserMain(main));

        tabPane.getTabs().add(tabHome);
        tabPane.getTabs().add(tabSearch);
        tabPane.getTabs().add(tabUser);

        VBox vBox = new VBox(tabPane);
        getChildren().addAll(vBox);
    }


}
