import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controller for the UI that prompts the user to update the question file
 */
public class UpdateQuestionController {

    @FXML
    private Button yesButton;

    @FXML
    private Text pythonStatus;

    public void initialize() {
        /**
         * Checks to see if python3 is installed on the system
         */
        try {
            // if this runs, python is installed
            Process p = Runtime.getRuntime().exec("python --version");
            pythonStatus.setText("Python is: Installed!");

        } catch (IOException e) {
            // this means python is not installed
            pythonStatus.setText("Python is: Not installed (or not in PATH)");
            yesButton.setDisable(true);

        }
    }

    public void updateQuestions() {
        try {
            // run the scraper
            pythonStatus.setText("Updating questions, please wait...");
            Process p = Runtime.getRuntime().exec("python questions/scrape_questions.py true");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        quit();

    }

    public void quit() {
        // load the "game" scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
            Stage primaryStage = (Stage) yesButton.getScene().getWindow();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
