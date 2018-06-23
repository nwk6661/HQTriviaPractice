import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main driver class for the Jfx application
 *
 * @author NK
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Load FXML
     * @param primaryStage jfx stage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent ui = FXMLLoader.load(getClass().getResource("/updateUI.fxml"));

        primaryStage.setTitle("HQ Practice");

        primaryStage.setScene(new Scene(ui));
        primaryStage.show();
    }
}
