package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Repository;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.Collection;

@Repository
public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

}
