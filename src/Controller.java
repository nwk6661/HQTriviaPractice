import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;

/**
 * Handle events from the view, such as button clicks
 *
 * @author NK
 */
public class Controller {

    private Model model;

    private final String originalStyle = "-fx-background-color: lightgrey;" +
            " -fx-background-radius: 20;" +
            " -fx-border-radius: 20; -fx-border-width: 5;";

    @FXML
    private Button answerOne;

    @FXML
    private Button answerThree;

    @FXML
    private Button answerTwo;

    @FXML
    private Label question;

    // Timer Label for countdown
    @FXML
    private Label timerLabel;

    @FXML
    private Label timeUp;

    private final int START_TIME = 10;

    private DoubleProperty timeRemaining = new SimpleDoubleProperty(START_TIME);

    // the button with the correct answer
    private Button correctButton;

    // see if the user has already answered
    private boolean answered;

    private Timeline timeline;

    private AudioClip timeFinishedSound;

    /**
     * Checks if a button is the correct answer
     * @param b the button we are checking
     * @return true if yes, false if no
     */
    public boolean checkAnswer(Button b) {
        return b.equals(correctButton);
    }

    /**
     * Event that runs when an answer button that has been clicked
     * Sets the style for buttons, red=wrong, green=right, orange=right answer if picked wrong
     * @param event the button event
     */
    public void answerSelected(ActionEvent event) {
        timeline.stop();
        // Make sure the user hasn't already picked an answer
        if(!answered && timeRemaining.getValue() > 0) {
            // Get the button where the event came from
            Button b = (Button) event.getSource();

            boolean correct = false;
            if (checkAnswer(b)) {
                correct = true;
            } else {
                correct = false;
            }

            // check each button to see if the one clicked is 1,2,3
            // check if it is the correct button, and update color accordingly
            if (b.equals(answerOne)) {
                if (correct) {
                    answerOne.setStyle(originalStyle + " -fx-background-color: lightgreen;");
                } else {
                    answerOne.setStyle(originalStyle + " -fx-background-color: indianred;");
                    correctButton.setStyle(originalStyle + " -fx-background-color: darkorange;");
                }
            } else if (b.equals(answerTwo)) {
                if (correct) {
                    answerTwo.setStyle(originalStyle + " -fx-background-color: lightgreen;");
                } else {
                    answerTwo.setStyle(originalStyle + " -fx-background-color: indianred;");
                    correctButton.setStyle(originalStyle + " -fx-background-color: darkorange;");
                }
            } else if (b.equals(answerThree)) {
                if (correct) {
                    answerThree.setStyle(originalStyle + " -fx-background-color: lightgreen;");
                } else {
                    answerThree.setStyle(originalStyle + " -fx-background-color: indianred;");
                    correctButton.setStyle(originalStyle + " -fx-background-color: darkorange;");
                }
            }

            // end temp

            answered = true;
        }
    }

    /**
     * reset the buttons for a new question
     */
    public void resetButtons() {
        answerOne.setStyle(originalStyle);
        answerTwo.setStyle(originalStyle);
        answerThree.setStyle(originalStyle);
        timerLabel.setOpacity(1);
        timeUp.setOpacity(0);
    }

    /**
     * Get a new question from the text file, and update the nodes
     */
    public void newQuestion() {
        try {
            timeline.stop();
        } catch (NullPointerException t) {
            System.out.println("Timer not running");
        }
        answered = false;
        resetButtons();
        startTimer();
        Question q = model.getRandomQuestion();
        question.setText(q.getQuestion());
        answerOne.setText(q.getAnswerOne());
        answerTwo.setText(q.getAnswerTwo());
        answerThree.setText(q.getAnswerThree());
        correctButton = findCorrect(q.getCorrectAnswer());
    }

    /**
     * Find the button that has the correct answer
     * @param answer: the answer we are looking for
     * @return the button that has the correct answer
     */
    private Button findCorrect(String answer) {
        if (answerOne.getText().equals(answer)) {
            return answerOne;
        } else if (answerTwo.getText().equals(answer)) {
            return answerTwo;
        } else {
            return answerThree;
        }

    }

    /**
     * Run on application start, pass controller to model and get a question
     */
    public void initialize() {
        model = new Model(this);
        // timerLabel.textProperty().bind(timeRemaining.asString());
        timerLabel.textProperty().bind(timeRemaining.asString("%.2f"));
        timeFinishedSound = new AudioClip(getClass().getResource("sound.wav").toExternalForm());
    }

    private void startTimer() {
        timeRemaining.set(START_TIME + 1);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(START_TIME + 1),
                new KeyValue(timeRemaining, 0))
        );
        timeline.play();
        timeline.setOnFinished(e -> {
            timerLabel.setOpacity(0);
            timeUp.setOpacity(1);
            timeFinishedSound.play();
            correctButton.setStyle(originalStyle + " -fx-background-color: darkorange;");
        });
    }


}
