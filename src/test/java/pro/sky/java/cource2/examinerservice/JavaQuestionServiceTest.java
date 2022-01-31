package pro.sky.java.cource2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;
import pro.sky.java.cource2.examinerservice.service.JavaQuestionService;

import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.cource2.examinerservice.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private final JavaQuestionService out = new JavaQuestionService();

    private static Question questionTest1;
    private static Question questionTest2;
    Set<Question> questionsForTest = new HashSet<>();

    @BeforeEach
    public void setUp() {
        questionTest1 = new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        questionTest2 = new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2);
        questionsForTest.add(questionTest1);
        questionsForTest.add(questionTest2);
    }

    @Test
    public void shouldAddToQuestionsSet() {
        assertEquals(questionTest1, out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1));
    }

    @Test
    public void shouldAddToQuestionsSetByObject() {
        assertEquals(questionTest1, out.add(questionTest1));
    }

    @Test
    public void shouldRemoveFromQuestionsSet() {
        assertEquals(new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2), out.remove(questionTest2));
    }

    @Test
    public void shouldGetAllQuestions() {
        out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        out.add(DEFAULT_QUESTION2,DEFAULT_ANSWER2);
        assertIterableEquals(questionsForTest, out.getAll());
    }

    @Test
    public void shouldThrowQuestionExistException() {
        out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        assertThrows(QuestionExistsException.class,
                ()-> out.add(DEFAULT_QUESTION1, DEFAULT_ANSWER1), "The q-a pair is already exist");
    }

    @Test
    public void shouldThrowQuestionNotFoundException() {
        out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        assertThrows(QuestionNotFoundException.class,
                ()-> out.remove(questionTest2), "Question is not found");
    }

    @Test
    public void shouldGetRandomQuestion() {
        out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        out.add(DEFAULT_QUESTION2,DEFAULT_ANSWER2);

        List<Question> questionsListForTest = new ArrayList<>(questionsForTest.size());
        questionsListForTest.addAll(questionsForTest);
        int randomIndex = nextInt(0, questionsListForTest.size());

        assertEquals(questionsListForTest.get(randomIndex), out.getRandomQuestion());

    }


}
