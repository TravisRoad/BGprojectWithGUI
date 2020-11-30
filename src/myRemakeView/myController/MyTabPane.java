package myRemakeView.myController;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

public class MyTabPane extends TabPane {
    ArrayList<Tab> tabList = new ArrayList<>();

    /**
     * @param tabNameList 传入标签列表
     */
    public MyTabPane(String[] tabNameList) {
        for (String tabName : tabNameList) {
            tabList.add(new Tab(tabName));
        }
    }


}
