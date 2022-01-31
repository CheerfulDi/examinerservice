package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionsOutOfBoundsException;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random = new Random();
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount != 0) {
            List<Question> questionsList = new ArrayList<>(questionService.getAll());
            if (amount <= questionsList.size()) {
                for (int i = 0; i < amount; i++) {
                    questionsList.add(questionService.getRandomQuestion());
                }
                return questionsList;
            }
        }
        throw new QuestionsOutOfBoundsException();
    }
}

