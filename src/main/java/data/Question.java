package data;

import java.util.List;

public class Question {

    private int id;
    private String question;
    private List<String> possibleAns;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPossibleAns() {
        return possibleAns;
    }

    public void setPossibleAns(List<String> possibleAns) {
        this.possibleAns = possibleAns;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String toStringPossibeAns(){
        StringBuilder allAns = new StringBuilder();
        for (String ans : possibleAns){
            allAns.append(ans).append("\n");
        }
        return allAns.toString();
    }
    @Override
    public String toString() {
        return "שאלה "+(id) +": "+question+"\n\n" + toStringPossibeAns();
    }
}
