package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
public class JavaQuestionService implements QuestionService {

    Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if (questions.contains(newQuestion)) {
            throw new QuestionExistsException();
        } else questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionExistsException();
        } else {
            questions.add(question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        } else {
            throw new QuestionNotFoundException();
        }
    }

    @Override
    public Collection<Question> getAll() {
        List<Question> questionsList = new ArrayList<>(questions.size());
        questionsList.addAll(questions);
        return questionsList;
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionsList = new ArrayList<>(questions.size());
        questionsList.addAll(questions);
        int randomIndex = nextInt(0, questionsList.size());
        return questionsList.get(randomIndex);
    }

}
