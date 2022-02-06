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
import pro.sky.java.cource2.examinerservice.service.MathQuestionRepository;
import pro.sky.java.cource2.examinerservice.service.MathQuestionService;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.java.cource2.examinerservice.TestConstants.*;
import static pro.sky.java.cource2.examinerservice.TestConstants.DEFAULT_ANSWER1;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    Set<Question> questionsForTest = new LinkedHashSet<>();

    @Mock
    MathQuestionRepository mathQuestionRepositoryMock;

    @InjectMocks
    MathQuestionService out;


    @BeforeEach
    public void setUp() {
        questionsForTest.add(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4));
        questionsForTest.add(new Question(DEFAULT_QUESTION5, DEFAULT_ANSWER5));
        questionsForTest.add(new Question(DEFAULT_QUESTION6, DEFAULT_ANSWER6));

        Mockito.when(mathQuestionRepositoryMock.getAll()).thenReturn(Collections.unmodifiableCollection(questionsForTest));

    }

    @Test
    public void shouldAddToQuestionsSet() {
        assertEquals(new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1), out.add(DEFAULT_QUESTION1,DEFAULT_ANSWER1));
    }

    @Test
    public void shouldAddToQuestionsSetByObject() {

        assertEquals(new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2),
                out.add(new Question(DEFAULT_QUESTION2,DEFAULT_ANSWER2)));
    }

    @Test
    public void shouldRemoveFromQuestionsSet() {

        assertEquals(new Question(DEFAULT_QUESTION4, DEFAULT_ANSWER4),
                out.remove(new Question(DEFAULT_QUESTION4,DEFAULT_ANSWER4)));
    }

    @Test
    public void shouldGetAllQuestions() {
        mathQuestionRepositoryMock.getAll();
        assertEquals(questionsForTest.size(), out.getAll().size());
    }

    @Test
    public void shouldThrowQuestionExistException() {
        mathQuestionRepositoryMock.getAll();
        assertThrows(QuestionExistsException.class,
                ()-> out.add(DEFAULT_QUESTION5, DEFAULT_ANSWER5), "The q-a pair is already exist");
    }

    @Test
    public void shouldThrowQuestionNotFoundException() {
        mathQuestionRepositoryMock.getAll();
        assertThrows(QuestionNotFoundException.class,
                ()-> out.remove(new Question(DEFAULT_QUESTION1,DEFAULT_ANSWER1)), "Question is not found");
    }

    @Test
    public void shouldGetRandomQuestion() {
        Mockito.when(out.getAll()).thenReturn(List.of(
                new Question(DEFAULT_QUESTION6,DEFAULT_ANSWER6)));

        assertEquals(new Question(DEFAULT_QUESTION6,DEFAULT_ANSWER6), out.getRandomQuestion());

    }
}
