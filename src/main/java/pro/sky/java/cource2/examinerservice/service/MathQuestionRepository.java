package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Repository;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    Set<Question> questions = new HashSet<>();

    @PostConstruct
    public void init() {
        new Question("2+2=?", "4");
        new Question("5*5=?", "25");
        new Question("27/3=?", "9");
    }

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
        return Set.copyOf(questions);
    }
}
