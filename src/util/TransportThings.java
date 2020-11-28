package util;

import model.BoardGameModel;
import model.User;

import java.io.Serializable;

public class TransportThings implements Serializable {
    private User user = null;
    private BoardGameModel boardGameModel = null;
    private String query = null;
    private String info = null;
    private int state = 0x00;//规定0x00代表查询失败，0x01代表成功

    public void setBoardGame(BoardGameModel boardGameModel) {
        this.boardGameModel = boardGameModel;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BoardGameModel getBoardGame() {
        return boardGameModel;
    }

    public String getQuery() {
        return query;
    }

    public User getUser() {
        return user;
    }

    public String getInfo() {
        return info;
    }

    public int getState() {
        return state;
    }
}
