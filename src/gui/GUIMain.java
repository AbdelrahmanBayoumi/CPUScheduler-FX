package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * GUIMain
 *
 * @author Abdelrahman Bayoumi
 */
public class GUIMain extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("CPU Scheduler Simulator");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.show();
        } catch (IOException e) {
            System.out.println("Exception in GUIMain -> Start Method:-" + e);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
