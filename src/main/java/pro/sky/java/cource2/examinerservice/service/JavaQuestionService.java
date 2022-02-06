package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if (javaQuestionRepository.getAll().contains(newQuestion)) {
            throw new QuestionExistsException();
        } else javaQuestionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (javaQuestionRepository.getAll().contains(question)) {
            throw new QuestionExistsException();
        } else {
            javaQuestionRepository.add(question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (javaQuestionRepository.getAll().contains(question)) {
            javaQuestionRepository.remove(question);
            return question;
        } else {
            throw new QuestionNotFoundException();
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(javaQuestionRepository.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionsList = new ArrayList<>(javaQuestionRepository.getAll().size());
        questionsList.addAll(javaQuestionRepository.getAll());
        int randomIndex = nextInt(0, questionsList.size());
        return questionsList.get(randomIndex);
    }

}
