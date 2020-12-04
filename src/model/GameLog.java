package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 游戏记录对象
 */
public class GameLog implements Serializable {
    private ArrayList<String> userNames = null; // 玩家列表
    private String organizer; // 组织者
    private String theVictoryOne = null;// 赢家
    private Date date = null;
    private int bg_id = 0;

    public void dateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String abcValue = sdf.format(date);
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(ArrayList<String> userNames) {
        this.userNames = userNames;
    }

    public String getTheVictoryOne() {
        return theVictoryOne;
    }

    public void setTheVictoryOne(String theVictoryOne) {
        this.theVictoryOne = theVictoryOne;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBg_id() {
        return bg_id;
    }

    public void setBg_id(int bg_id) {
        this.bg_id = bg_id;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @Override
    public String toString() {
        return "GameLog{" +
                "userNames=" + userNames +
                ", organizer='" + organizer + '\'' +
                ", theVictoryOne='" + theVictoryOne + '\'' +
                ", date=" + date +
                ", bg_id=" + bg_id +
                '}';
    }
}
