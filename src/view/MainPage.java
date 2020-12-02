package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import transport.ClientTrans;

import java.io.IOException;

/**
 * @deprecated
 */
public class MainPage extends Application {

    private ClientTrans clientTrans;
    private User currentUser;
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
    }

    public void setClientTrans(ClientTrans clientTrans) {
        this.clientTrans = clientTrans;
    }

    public ClientTrans getClientTrans() {
        return clientTrans;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean connect2server() {
        clientTrans = new ClientTrans();
        return clientTrans.connect();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Stage getStage(){
        return this.mainStage;
    }

    public void setStage(Stage stage){
        this.mainStage = stage;
    }

}
