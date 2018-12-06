package logic;

import data.Answer;
import data.EStatus;
import data.Question;
import info.HTTP;

import java.util.Scanner;

public class Riddler {
     private Scanner reader = new Scanner(System.in);
     private int score;
     private String name;
     private HTTP http;


    public void init(){
        System.out.println("מה השם שלכם ?");
        name = reader.next();
        http = new HTTP("http://10.200.103.105:5000");
    }
    public  Answer ask(Question question){
        System.out.println(question.toString());
        return new Answer(question.getId(),Integer.valueOf(reader.next()));
    }
    public void submit(EStatus response,int factor)  {
        int add = factor == 0 ? response.getStatus() : response.getStatus() /factor;
        score += add;
        System.out.println(response+"\n");
        report(score);

    }

    public int getScore() {
        return score;
    }

    private void report(int score){
        http.post(name,score);
    }
}
