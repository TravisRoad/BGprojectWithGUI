package view.myLayout;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import transport.ClientTrans;
import view.myLayout.HomeStream;
import view.myLayout.SearchMain;
import view.myLayout.UserMain;

public class MainTabLayout extends VBox {
    public MainTabLayout() {
        ClientTrans ct = new ClientTrans();
        TabPane tabPane = new TabPane();
        
        Tab tabHome = new Tab("Home", new HomeStream(ct));
        Tab tabSearch = new Tab("Search", new SearchMain(ct));
        Tab tabUser = new Tab("User", new UserMain());

        tabPane.getTabs().add(tabHome);
        tabPane.getTabs().add(tabSearch);
        tabPane.getTabs().add(tabUser);

        VBox vBox = new VBox(tabPane);
        //Scene scene = new Scene(vBox);
        getChildren().addAll(vBox);
    }
}
