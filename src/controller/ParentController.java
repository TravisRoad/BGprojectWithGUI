package controller;

import transport.ClientTrans;
import util.Database;
import view.MainPage;

public class ParentController {
    protected ClientTrans clientTrans;
    protected MainPage mainPage;

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }

    public ClientTrans getClientTrans() {
        return clientTrans;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }
}
