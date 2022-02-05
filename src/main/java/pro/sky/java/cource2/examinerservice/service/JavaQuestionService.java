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
        if (javaQuestionRepository.questions.contains(newQuestion)) {
            throw new QuestionExistsException();
        } else javaQuestionRepository.questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (javaQuestionRepository.questions.contains(question)) {
            throw new QuestionExistsException();
        } else {
            javaQuestionRepository.questions.add(question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (javaQuestionRepository.questions.contains(question)) {
            javaQuestionRepository.questions.remove(question);
            return question;
        } else {
            throw new QuestionNotFoundException();
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(javaQuestionRepository.questions);
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionsList = new ArrayList<>(javaQuestionRepository.questions.size());
        questionsList.addAll(javaQuestionRepository.questions);
        int randomIndex = nextInt(0, questionsList.size());
        return questionsList.get(randomIndex);
    }

}
