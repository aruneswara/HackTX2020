import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;

public class FastJava  {

    private int score;
    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private int current;
    private Stack<Integer> num;
    private int questionsAnswered;

    public FastJava(int d) throws IOException {
        num = new Stack<Integer>();
        current = -1;
        Scanner reader = null;
        score = 0;
        if (d == 0) {
            reader = new Scanner(new FileReader("easy.txt"));
            readFile(reader);
            reader = new Scanner(new FileReader("medium.txt"));
            readFile(reader);
            reader = new Scanner(new FileReader("hard.txt"));
            readFile(reader);
        }
        if (d == 1) {
            reader = new Scanner(new FileReader("easy.txt"));
            readFile(reader);
        }
        if (d == 2) {
            reader = new Scanner(new FileReader("medium.txt"));
            readFile(reader);
        }
        if (d == 3) {
            reader = new Scanner(new FileReader("hard.txt"));
            readFile(reader);
        }

        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i<questions.size(); i++)
            temp.add(i);
        Random rand = new Random();
        while(!temp.isEmpty())
        {
            int x = rand.nextInt(temp.size());
            num.push(temp.get(x));
            temp.remove(x);
        }

        for (String s : answers) {
            System.out.println(s);
        }


    }

    private void readFile(Scanner reader) {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (line.equals("-Q")) {
                StringBuilder sb = new StringBuilder();
                String newLine = reader.nextLine();
                while (!newLine.equals("-A")) {
                    sb.append(newLine + "\n");
                    newLine = reader.nextLine();
                }
                questions.add(sb.toString());
                StringBuilder sba = new StringBuilder();
                newLine = reader.nextLine();
                while (reader.hasNextLine() && !newLine.equals("~")) {
                    sba.append(newLine + "\n");
                    newLine = reader.nextLine();
                }
                answers.add(sba.toString());
            }
        }
        reader.close();
    }

    public String getQuestion() {

        if (num.isEmpty())
        {
            current = -1;
            return null;
        }
        questionsAnswered++;
        current = num.pop();
        return questions.get(current);
    }

    public int getQuestionNum()
    {
        return current;
    }

    public String getQuestion(int n) {
        current = n;
        return questions.get(n);
    }

    public String getAnswer(){
        if (current == -1)
            return null;
        return answers.get(current);
    }

    public boolean checkAnswer(String ans, long n) {
        if (n > 5) {
            return false;
        }
        if(current==-1)
            return false;
        if (ans.equals(answers.get(current))) {
            score++;
            return true;
        }
        return false;
    }



    public float getScore() {
        if(questionsAnswered==0)
            return 0;
        return (float) (((float)score)/questionsAnswered * 100.00);
    }



}
