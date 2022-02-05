package pro.sky.java.cource2.examinerservice.service;

import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

}
