package model;

import java.io.Serializable;

/**
 *
 */
public class BoardGameModel implements Serializable {
    private int bg_id;
    private String name;
    private String introduction;
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public BoardGameModel(int bg_id, String name, String introduction, Double rate) {
        this.bg_id = bg_id;
        this.name = name;
        this.introduction = introduction;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public long getBg_id() {
        return bg_id;
    }

    public void setBg_id(int bg_id) {
        this.bg_id = bg_id;
    }
}
