package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import util.TransportThings;
import view.LoginPane;
import view.MainPage;


public class MainController extends ParentController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button ButtonSearch;
    @FXML
    private Button ButtonEditProfile;
    @FXML
    private Label LabelUsername;
    @FXML
    private Tab TabHome;
    @FXML
    private Tab TabSearch;
    @FXML
    private Tab TabUser;
    @FXML
    private Tab TabSettings;
    @FXML
    private TextField FieldSearch;
    private String str = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    public void open_login_window(ActionEvent event) throws IOException {
        LoginPane lp = new LoginPane();
        lp.setMainPage(mainPage);
        lp.startLoginPane(clientTrans);
        change_scene_user(null);

    }

    public void change_scene_user(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resource/Main.fxml"));
        Parent root = fxmlLoader.load();
        Scene s = new Scene(root);
        MainController mainController = fxmlLoader.getController();
        mainController.setMainPage(mainPage);
        mainPage.getStage().setScene(s);
    }

    public void on_button_edit_profile_clicked(ActionEvent event) throws IOException {
        //TODO
    }

    public void refresh_scene(Event event) throws IOException {
        //When tab selection changed
        //TODO
    }

    public void setStr(String str) {
        this.str = str;
    }

    /*public TextField getFieldSearch() {
        return FieldSearch;
    }*/



    public void on_button_search_clicked(ActionEvent event) throws IOException {
        //
        //TODO: Search for board game
        TransportThings tt = new TransportThings();
        tt.setQuery("search");
        CharSequence searchStr = FieldSearch.getCharacters();
        tt.setInfo(searchStr.toString());
        mainPage.getClientTrans().writeObj(tt);
        tt = (TransportThings) mainPage.getClientTrans().readObj();
        int state = tt.getState();
        if(state == 0x00){
            System.out.println("failed");
        }
    }
}

