package pro.sky.java.cource2.examinerservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionsOutOfBoundsException;
import pro.sky.java.cource2.examinerservice.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random = new Random();
    private final QuestionService javaService;
    private final QuestionService mathService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService")QuestionService javaService,
                               @Qualifier("mathQuestionService")QuestionService mathService) {
        this.javaService = javaService;
        this.mathService = mathService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<QuestionService> services = List.of(javaService,mathService);
        int size = javaService.getAll().size()+ mathService.getAll().size();
        if (amount <= 0 || size < amount) {
            throw new QuestionsOutOfBoundsException();
        }
        Set<Question> uniqueQuestions = new HashSet<>(amount);
            while (uniqueQuestions.size() < amount) {
                QuestionService questionService = services.get(random.nextInt(services.size()));
                uniqueQuestions.add(questionService.getRandomQuestion());
            }
            return uniqueQuestions;
        }

    }


