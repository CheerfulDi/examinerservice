package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MathQuestionRepository implements QuestionRepository {

    private final QuestionRepository questionRepository;
    Set<Question> questions = new HashSet<>();

    public MathQuestionRepository (QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    public void init() {

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
        return Set.copyOf(questions);
    }
}
