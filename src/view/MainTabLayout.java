package view;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import transport.ClientTrans;

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
        Scene scene = new Scene(vBox);
    }
}
