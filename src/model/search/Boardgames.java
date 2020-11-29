/**
 * Copyright 2020 bejson.com
 */
package model.search;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-11-29 1:4:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Boardgames implements Serializable {

    private List<Boardgame> boardgame;
    private String termsofuse;

    public void setBoardgame(List<Boardgame> boardgame) {
        this.boardgame = boardgame;
    }

    public List<Boardgame> getBoardgame() {
        return boardgame;
    }

    public void setTermsofuse(String termsofuse) {
        this.termsofuse = termsofuse;
    }

    public String getTermsofuse() {
        return termsofuse;
    }

}