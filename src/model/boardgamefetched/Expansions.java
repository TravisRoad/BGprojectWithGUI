/**
 * Copyright 2020 bejson.com
 */
package model.boardgamefetched;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-28 18:54:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Expansions implements Serializable {

    private String name;
    private int gameId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

}