package model;

import java.io.Serializable;

/**
 * 桌游详情页面
 */
public class BoardGameModel implements Serializable {
    private int bg_id;
    private String name;
    private String introduction;
    private Double rate;

    public BoardGameModel() {
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * 构造桌游模型
     *
     * @param bg_id        桌游id
     * @param name         桌游名称
     * @param introduction 桌游介绍
     * @param rate         桌游评分
     */
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

    @Override
    public String toString() {
        return "BoardGameModel{" +
                "bg_id=" + bg_id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", rate=" + rate +
                '}';
    }
}
