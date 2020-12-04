package util;

import model.BoardGameModel;
import model.GameLog;
import model.User;
import model.boardgamefetched.BoardGameFetched;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 带有各种 model类对象的运载类，用于在服务端和客户端之间传递信息
 */
public class TransportThings implements Serializable {
    private User user = null;
    private BoardGameModel boardGameModel = null;
    private BoardGameFetched boardGameFetched = null;
    private ArrayList<BoardGameModel> boardGameModels = null;
    private ArrayList<GameLog> gameLogs = null;
    private GameLog gameLog = null;

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

    public BoardGameModel getBoardGameModel() {
        return boardGameModel;
    }

    public void setBoardGameModel(BoardGameModel boardGameModel) {
        this.boardGameModel = boardGameModel;
    }

    public GameLog getGameLog() {
        return gameLog;
    }

    public void setGameLog(GameLog gameLog) {
        this.gameLog = gameLog;
    }

    public ArrayList<GameLog> getGameLogs() {
        return gameLogs;
    }

    public void setGameLogs(ArrayList<GameLog> gameLogs) {
        this.gameLogs = gameLogs;
    }

    @Override
    public String toString() {
        return "TransportThings{" +
                "user=" + user +
                ", boardGameModel=" + boardGameModel +
                ", boardGameFetched=" + boardGameFetched +
                ", boardGameModels=" + boardGameModels +
                ", gameLogs=" + gameLogs +
                ", gameLog=" + gameLog +
                ", query='" + query + '\'' +
                ", info='" + info + '\'' +
                ", state=" + state +
                '}';
    }
}
