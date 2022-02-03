package pro.sky.java.cource2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionsOutOfBoundsException;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random = new Random();
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > 0) {
            Set<Question> uniqueQuestions = new HashSet<>(amount);
            while (uniqueQuestions.size() <= amount) {
                uniqueQuestions.add(questionService.getRandomQuestion());
            }
            return uniqueQuestions;
        }
        throw new QuestionsOutOfBoundsException();
    }
    }


