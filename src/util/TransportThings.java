package util;

import model.BoardGameModel;
import model.User;
import model.boardgamefetched.BoardGameFetched;
import model.search.BoardGameSearched;

import java.io.Serializable;
import java.util.ArrayList;

public class TransportThings implements Serializable {
    private User user = null;
    private BoardGameModel boardGameModel = null;
    private BoardGameFetched boardGameFetched = null;
    private ArrayList<BoardGameModel> boardGameModels = null;

    public ArrayList<BoardGameModel> getBoardGameModels() {
        return boardGameModels;
    }

    public void setBoardGameModels(ArrayList<BoardGameModel> boardGameModels) {
        this.boardGameModels = boardGameModels;
    }


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

    public BoardGameFetched getBoardGameFetched() {
        return boardGameFetched;
    }

    public void setBoardGameFetched(BoardGameFetched boardGameFetched) {
        this.boardGameFetched = boardGameFetched;
    }

}
