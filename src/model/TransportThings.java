package model;

import java.io.Serializable;

public class TransportThings implements Serializable {
    private User user;
    private BoardGame boardGame;
    private String classflag; // 传输的对象类型
    private String method; // 用于表示需要使用的方法

    public TransportThings(User user, BoardGame boardGame, String classflag, String method){
        this.user = user;
        this.boardGame = boardGame;
        this.classflag = classflag;
        this.method = method;
    }

}
