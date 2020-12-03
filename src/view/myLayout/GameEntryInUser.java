package view.myLayout;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;

/**
 * user中的桌游条目，继承基础条目类型，不显示评分而是显示日期和玩家。
 */
public class GameEntryInUser extends GameEntry {

    public GameEntryInUser(String imageURL, String title, String intro, Date playDate, ArrayList<String> userName) {
        super();//调用父类构造方法填入桌游基本信息
        this.imageURL = imageURL;
        this.title = title;
        this.intro = intro;
        setLayout();//复用父类中的函数，设置图片及介绍layout
        setLastPlayed(playDate, userName);//设置日期及玩家layout
    }

    //设置日期及玩家layout
    private void setLastPlayed(Date playDate, ArrayList<String> userNames) {
        VBox vBox = new VBox(5);
        vBox.getChildren().add(new Label(playDate.toString()));
        for (String userName : userNames) {
            vBox.getChildren().add(new Label(userName));
        }
        this.getChildren().add(vBox);
    }


}
