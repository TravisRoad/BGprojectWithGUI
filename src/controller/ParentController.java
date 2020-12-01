package controller;

import transport.ClientTrans;
import util.Database;
import view.MainPage;

public class ParentController {
    protected ClientTrans clientTrans;

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }

    public ClientTrans getClientTrans() {
        return clientTrans;
    }
}
