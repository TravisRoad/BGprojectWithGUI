package model.search;

import java.io.Serializable;

/**
 * 自动生成
 * Auto-generated: 2020-11-29 1:4:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Boardgame implements Serializable {

    private int yearpublished;
    private Name name;
    private long objectid;

    public void setYearpublished(int yearpublished) {
        this.yearpublished = yearpublished;
    }

    public int getYearpublished() {
        return yearpublished;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setObjectid(long objectid) {
        this.objectid = objectid;
    }

    public long getObjectid() {
        return objectid;
    }

}