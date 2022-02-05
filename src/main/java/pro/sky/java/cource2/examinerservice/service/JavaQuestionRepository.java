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
public class JavaQuestionRepository implements QuestionRepository{


    Set<Question> questions = new HashSet<>();

    @PostConstruct
    public void init() {
        questions.add(new Question("Что такое наследование?", "Под наследованием подразумевается, что один класс может наследовать другой класс."));
        questions.add(new Question("Что такое инкапсуляция?", "Инкапсуляция — это сокрытие реализации при помощи модификаторов доступа, при помощи геттеров и сеттеров."));
        questions.add(new Question("Что такое полиморфизм?", "Полиморфизм — это способность программы идентично использовать объекты с одинаковым интерфейсом без информации о конкретном типе этого объекта."));
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
