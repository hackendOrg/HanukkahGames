package data;

public class Question {

    private int id;
    private String question;

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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "שאלה "+(id+1) +": "+question;
    }
}
