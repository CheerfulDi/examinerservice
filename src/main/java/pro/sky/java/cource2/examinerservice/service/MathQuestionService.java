package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
public class MathQuestionService implements QuestionService{

    private final MathQuestionRepository mathQuestionRepository;

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if (mathQuestionRepository.getAll().contains(newQuestion)) {
            throw new QuestionExistsException();
        } else mathQuestionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (mathQuestionRepository.getAll().contains(question)) {
            throw new QuestionExistsException();
        } else {
            mathQuestionRepository.add(question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (mathQuestionRepository.getAll().contains(question)) {
            mathQuestionRepository.remove(question);
            return question;
        } else {
            throw new QuestionNotFoundException();
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(mathQuestionRepository.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionsList = new ArrayList<>(mathQuestionRepository.getAll());
        int randomIndex = nextInt(0, questionsList.size());
        return questionsList.get(randomIndex);
    }
}
