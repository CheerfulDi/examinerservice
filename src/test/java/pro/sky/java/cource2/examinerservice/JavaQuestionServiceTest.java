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

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.cource2.examinerservice.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    Set<Question> questionsForTest = new LinkedHashSet<>();

    @Mock
    JavaQuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    JavaQuestionService out;


    @BeforeEach
    public void setUp() {
        questionsForTest.add(new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1));
        questionsForTest.add(new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2));
        questionsForTest.add(new Question(DEFAULT_QUESTION3, DEFAULT_ANSWER3));

        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(Collections.unmodifiableCollection(questionsForTest));

    }

    @Test
    public void shouldAddToQuestionsSet() {
        assertEquals(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4), out.add(DEFAULT_QUESTION4,DEFAULT_ANSWER4));
    }

    @Test
    public void shouldAddToQuestionsSetByObject() {

        assertEquals(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4),
                out.add(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4)));
    }

    @Test
    public void shouldRemoveFromQuestionsSet() {

        assertEquals(new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2),
                out.remove(new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2)));
    }

    @Test
    public void shouldGetAllQuestions() {
        javaQuestionRepositoryMock.getAll();
        assertEquals(questionsForTest.size(), out.getAll().size());
    }

    @Test
    public void shouldThrowQuestionExistException() {
        javaQuestionRepositoryMock.getAll();
        assertThrows(QuestionExistsException.class,
                ()-> out.add(DEFAULT_QUESTION3, DEFAULT_ANSWER3), "The q-a pair is already exist");
    }

    @Test
    public void shouldThrowQuestionNotFoundException() {
        javaQuestionRepositoryMock.getAll();
        assertThrows(QuestionNotFoundException.class,
                ()-> out.remove(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4)), "Question is not found");
    }

    @Test
    public void shouldGetRandomQuestion() {
        Mockito.when(out.getAll()).thenReturn(List.of(
                new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1)));

        assertEquals(new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1), out.getRandomQuestion());

    }


}
