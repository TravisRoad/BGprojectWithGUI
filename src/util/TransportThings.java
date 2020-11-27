package util;

import model.BoardGame;
import model.User;

import java.io.Serializable;

public class TransportThings implements Serializable {
    private User user;
    private BoardGame boardGame;
    private String query;

    public void setBoardGame(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BoardGame getBoardGame() {
        return boardGame;
    }

    public String getQuery() {
        return query;
    }

    public User getUser() {
        return user;
    }
}
