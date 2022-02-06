package pro.sky.java.cource2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.cource2.examinerservice.model.Question;
import pro.sky.java.cource2.examinerservice.service.JavaQuestionRepository;
import pro.sky.java.cource2.examinerservice.service.JavaQuestionService;

import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.cource2.examinerservice.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    Set<Question> questionsForTest = new HashSet<>();

    @Mock
    JavaQuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    JavaQuestionService out;


    @BeforeEach
    public void setUp() {
        questionsForTest.add(new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1));
        questionsForTest.add(new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2));
        questionsForTest.add(new Question(DEFAULT_QUESTION3, DEFAULT_ANSWER3));

        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(List.of(
                new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1),
                new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2),
                new Question(DEFAULT_QUESTION3, DEFAULT_ANSWER3)
    ));
    }

    @Test
    public void shouldAddToQuestionsSet() {

        Mockito.when(javaQuestionRepositoryMock.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4)).thenReturn(
                new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4));
        assertEquals(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4), out.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4));
    }

    @Test
    public void shouldAddToQuestionsSetByObject() {

        Mockito.when(javaQuestionRepositoryMock.add(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4))).thenReturn(
                new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4));

        assertEquals(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4),
                out.add(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4)));
    }

    @Test
    public void shouldRemoveFromQuestionsSet() {
        Mockito.when(javaQuestionRepositoryMock.remove(new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2))).thenReturn(
                new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2));
        assertEquals(new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2),
                out.remove(new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2)));
    }

    @Test
    public void shouldGetAllQuestions() {
        javaQuestionRepositoryMock.getAll();
        out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        out.add(DEFAULT_QUESTION2,DEFAULT_ANSWER2);
        out.add(DEFAULT_QUESTION3,DEFAULT_ANSWER3);
        assertIterableEquals(questionsForTest, out.getAll());
    }

    @Test
    public void shouldThrowQuestionExistException() {
        Mockito.when(javaQuestionRepositoryMock.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4)).thenReturn(
                new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4));
        out.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4);
        assertThrows(QuestionExistsException.class,
                ()-> out.add(DEFAULT_QUESTION4, DEFAULT_ANSWER4), "The q-a pair is already exist");
    }

    @Test
    public void shouldThrowQuestionNotFoundException() {
        Mockito.when(javaQuestionRepositoryMock.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4)).thenReturn(
                new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4));
        out.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4);
        assertThrows(QuestionNotFoundException.class,
                ()-> out.remove(new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2)), "Question is not found");
    }

    @Test
    public void shouldGetRandomQuestion() {
        Mockito.when(javaQuestionRepositoryMock.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4)).thenReturn(
                new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1),
                new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2)
        );
        out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1);
        out.add(DEFAULT_QUESTION2,DEFAULT_ANSWER2);

        List<Question> questionsListForTest = new ArrayList<>(questionsForTest.size());
        questionsListForTest.addAll(questionsForTest);
        int randomIndex = nextInt(0, questionsListForTest.size());

        assertEquals(questionsListForTest.get(randomIndex), out.getRandomQuestion());

    }


}
