package view.myStage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @deprecated
 */
public class ProgressFrom {
    private Stage dialogStage;
    private ProgressIndicator progressIndicator;

    public ProgressFrom(Stage primaryStage) {
        dialogStage = new Stage();

        // 窗口父子关系
        dialogStage.initOwner(primaryStage);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        // progress bar
        progressIndicator = new ProgressIndicator();
        Label label = new Label("数据加载中, 请稍后...");
        label.setTextFill(Color.BLUE);
        progressIndicator.setProgress(-1F);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator, label);
        Scene barScene = new Scene(vBox);
        barScene.setFill(null);
        dialogStage.setScene(barScene);
    }

    public void activateProgressBar() {
        dialogStage.show();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void cancelProgressBar() {
        dialogStage.close();
    }
}