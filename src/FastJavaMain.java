import jdk.nashorn.internal.scripts.JO;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.io.IOException;
import javax.swing.Timer;
import java.awt.event.*;

public class FastJavaMain {
    public static void main(String[] args) {
        long startTime = 0;
        JTextField difficulty = new JTextField();
        Object[] message = {"Welcome to Fast Java!\nWhich difficulty do you want to practice with? (Easy, Medium, Hard)", difficulty};
        boolean validInput = false;
        int numDifficulty = 0;
        FastJava game = null;
        do {
            JOptionPane.showConfirmDialog(null, message, "Menu", JOptionPane.OK_CANCEL_OPTION);
            String diffParam = difficulty.getText().toLowerCase();
            switch (diffParam) {
                case "easy": validInput = true; numDifficulty = 1; break;
                case "medium": validInput = true; numDifficulty = 2; break;
                case "hard": validInput = true; numDifficulty = 3; break;
                default: validInput = false;
            }
        } while(!validInput);
        boolean keepPlaying = true;
        try {
            game = new FastJava(numDifficulty);
        } catch (IOException e) {
            System.err.println("Unable to create game: " + e);
            keepPlaying = false;
        }
        //Easy difficulty
        while(keepPlaying) {
            String answer = "";

            String question = game.getQuestion();
            if (question == null) {
                keepPlaying = false;
                continue;
            }
            if (numDifficulty == 1) {

                String[] multipleChoice = {"A", "B", "C"};
                startTime = System.nanoTime();
                answer = (String) JOptionPane.showInputDialog(null, question, "Pick the correct answer:", JOptionPane.DEFAULT_OPTION, null, multipleChoice, "A");

            } else {
                //Medium and hard stuff
                JTextField input = new JTextField();
                Object[] inputField = {question, input};
                startTime = System.nanoTime();
                JOptionPane.showConfirmDialog(null, inputField, "Menu", JOptionPane.OK_CANCEL_OPTION);
                answer = input.getText();
            }
            answer += "\n";
            System.out.println(answer);
            String response = "";
            long endTime = (System.nanoTime() - startTime) / 1000000000;
            System.out.println(endTime);
            if (game.checkAnswer(answer, endTime))
                response = "You got the answer correct!";
            else if(endTime > 5)
                response = "You took too long to answer.";
            else
                response = "You got the question incorrect";
            int score = (int) (game.getScore());
            response += "\nYour score: " + score + "%";
            JOptionPane.showMessageDialog(null, response);
        }
        int score = (int) (game.getScore());
        JOptionPane.showMessageDialog(null, "Good job! Your total score is: " + score + "%");

}
}
