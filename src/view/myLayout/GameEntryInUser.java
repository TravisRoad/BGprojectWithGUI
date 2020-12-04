package view.myLayout;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;

/**
 * user中的桌游条目，继承基础条目类型，不显示评分而是显示日期和玩家。
 *
 * @author Ridd
 */
public class GameEntryInUser extends GameEntry {

    /**
     * 构造user界面中的桌游条目
     *
     * @param imageURL 图片地址
     * @param title    桌游标题
     * @param intro    桌游简介
     * @param playDate 游玩日期
     * @param userName 用户名
     */
    public GameEntryInUser(String imageURL, String title, String intro, Date playDate, ArrayList<String> userName) {
        super();//调用父类构造方法填入桌游基本信息
        this.imageURL = imageURL;
        this.title = title;
        this.intro = intro;
        setLayout();//复用父类中的函数，设置图片及介绍layout
        setLastPlayed(playDate, userName);//设置日期及玩家layout
    }

    /**
     * 设置日期及玩家layout
     *
     * @param playDate  游玩日期
     * @param userNames 用户名列表
     */
    private void setLastPlayed(Date playDate, ArrayList<String> userNames) {
        VBox vBox = new VBox(5);
        vBox.getChildren().add(new Label(playDate.toString()));
        for (String userName : userNames) {
            vBox.getChildren().add(new Label(userName));
        }
        this.getChildren().add(vBox);
    }
}
