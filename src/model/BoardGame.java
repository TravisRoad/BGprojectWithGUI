package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BoardGame implements Serializable {
    private long ID;
    private long rank;
    private String name;
    private ArrayList<String> tags;
    //TODO: 可能会有多个文件路径
    private String picPath;//考虑使用java中的包装类

    public void setRank(long rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
