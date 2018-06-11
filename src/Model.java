import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Primary purpose of this class is to read questions from text file and generate random
 * question objects to be loaded into the application
 *
 * @author NK
 */
public class Model {
    private BufferedReader in;
    private ArrayList<Question> questions = new ArrayList<>();
    private Controller controller;
    private Random randomGenerator;

    public Model(Controller controller) {
        initialize();
        this.controller = controller;
    }

    private void initialize() {
        try {
            // badly named temps for splitting strings from file
            String tempLine;
            String[] temp;
            String[] tempList;
            String text;

            // TODO: 6/10/2018 fix this so no unicode diamond
            // initialize the bufferedreader to read from file
            in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("resources/questions.txt"), "UTF-8"));
            tempLine = in.readLine();
            // loop through the file until no more lines
            while (tempLine != null) {
                // split the question from the answer
                tempList = tempLine.split("\\|");
                text = tempList[0];
                // split answers string into seperate answers [a1, a2, a3, correct]
                temp = tempList[1].split(",");
                // make a new question object from the parsed data
                questions.add(new Question(text, temp[0], temp[1], temp[2], temp[3]));
                // read the next line
                tempLine = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        randomGenerator = new Random();
    }

    /**
     * Pull a random question from the list of questions
     * @return the randomly selected question
     */
    public Question getRandomQuestion() {
        int index = randomGenerator.nextInt(questions.size());
        return questions.get(index);
    }



}
