package pro.sky.java.cource2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.cource2.examinerservice.model.Question;
import pro.sky.java.cource2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController

@RequestMapping("/math")
public class MathQuestionController {

    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question removedQuestion = new Question(question, answer);
        questionService.remove(removedQuestion);
        return "Question: " + removedQuestion + " removed";
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
