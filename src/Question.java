/**
 * Question object to store the question, answers, and correct answers
 *
 * @author NK
 */
public class Question {
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String correctAnswer;

    public Question(String question, String answerOne, String answerTwo, String answerThree,
                    String correctAnswer) {
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
