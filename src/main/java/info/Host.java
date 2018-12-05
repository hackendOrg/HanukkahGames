package info;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Answer;
import data.EStatus;
import data.Question;
import logic.Riddler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Host {
    private Map<Integer, Question> questions;
    private Map<Integer, Answer> answers;
    private Riddler riddler;
    private int qCount = 0;

    public Host(String path) throws IOException {
        questions = new HashMap<>();
        answers = new HashMap<>();

        HashMap rawJson = new ObjectMapper().readValue(new File(path), HashMap.class);
        for (Object obj : rawJson.keySet()) {
            String key = (String) obj;
            String question = (String) ((ArrayList) rawJson.get(key)).get(0);
            String answer = (String) ((ArrayList) rawJson.get(key)).get(1);
            questions.put(Integer.valueOf(key), new Question(Integer.valueOf(key), question));
            answers.put(Integer.valueOf(key), new Answer(Integer.valueOf(key), answer));
        }
        riddler = new Riddler();

    }

    public void startTheShow() {
        riddler.init();
        boolean shouldContinue = true;
        while(shouldContinue) {
            Answer answer = riddler.ask(questions.get(qCount++));
            EStatus status = EStatus.WRONG;
            if (answer.getAnswer().equals(answers.get(answer.getId()).getAnswer())) {
                status = EStatus.CORRECT;

            }
           riddler.submit(status);
            shouldContinue = questions.get(qCount) != null;
        }

        System.out.println("Final Score: "+riddler.getScore());
    }
}
