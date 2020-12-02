package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameLog {
    private ArrayList<String> userNames = null;
    private String theVictoryOne = null;
    private Date date = null;

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
}
