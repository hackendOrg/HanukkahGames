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
import java.util.List;
import java.util.Map;

public class Host {
    private Map<Integer, Question> questions;
    private Map<Integer, Answer> answers;
    private Riddler riddler;
    private int qCount = 1;
    private final String QUESTION_INDEX = "question";
    private final String ANSWER_INDEX = "answer";
    private final String OPTIONS_INDEX = "options";

    public Host(String path) throws IOException {
        questions = new HashMap<>();
        answers = new HashMap<>();

        HashMap rawJson = new ObjectMapper().readValue(new File(path), HashMap.class);

        for (Object obj : rawJson.keySet()) {
            String key = (String) obj;
            HashMap body = (HashMap) rawJson.get(key);

            Question question = new Question(Integer.valueOf(key), (String) body.get(QUESTION_INDEX));
            question.setPossibleAns((List<String>) body.get(OPTIONS_INDEX));
            questions.put(Integer.valueOf(key), question);
            answers.put(Integer.valueOf(key), new Answer(Integer.valueOf(key), Integer.valueOf((String) body.get(ANSWER_INDEX))));

        }
        riddler = new Riddler();

    }

    public void startTheShow() {
        riddler.init();
        boolean shouldContinue = true;
        while(shouldContinue) {
            Answer answer = riddler.ask(questions.get(qCount++));
            EStatus status = EStatus.WRONG;
            if (answer.getAnswer() == (answers.get(answer.getId()).getAnswer())) {
                status = EStatus.CORRECT;

            }
           riddler.submit(status);
            shouldContinue = questions.get(qCount) != null;
        }

        System.out.println("Final Score: "+riddler.getScore());
    }
}
