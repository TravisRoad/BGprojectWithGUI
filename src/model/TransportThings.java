package model;

import java.io.Serializable;

/**
 * @deprecated
 */
public class TransportThings implements Serializable {
    private User user;
    private BoardGameModel boardGameModel;
    private String classflag; // 传输的对象类型
    private String method; // 用于表示需要使用的方法

    public TransportThings(User user, BoardGameModel boardGameModel, String classflag, String method) {
        this.user = user;
        this.boardGameModel = boardGameModel;
        this.classflag = classflag;
        this.method = method;
    }
}
