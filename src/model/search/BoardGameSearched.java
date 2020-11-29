/**
 * Copyright 2020 bejson.com
 */
package model.search;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-29 1:4:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BoardGameSearched implements Serializable {

    private Boardgames boardgames;

    public void setBoardgames(Boardgames boardgames) {
        this.boardgames = boardgames;
    }

    public Boardgames getBoardgames() {
        return boardgames;
    }

}