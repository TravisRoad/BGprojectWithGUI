package model.boardgamefetched;

import java.io.Serializable;

/**
 * 自动生成
 * Auto-generated: 2020-11-28 18:54:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PlayerPollResults implements Serializable {

    private int numPlayers;
    private int best;
    private int recommended;
    private int notRecommended;
    private boolean numPlayersIsAndHigher;

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setBest(int best) {
        this.best = best;
    }

    public int getBest() {
        return best;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setNotRecommended(int notRecommended) {
        this.notRecommended = notRecommended;
    }

    public int getNotRecommended() {
        return notRecommended;
    }

    public void setNumPlayersIsAndHigher(boolean numPlayersIsAndHigher) {
        this.numPlayersIsAndHigher = numPlayersIsAndHigher;
    }

    public boolean getNumPlayersIsAndHigher() {
        return numPlayersIsAndHigher;
    }

}