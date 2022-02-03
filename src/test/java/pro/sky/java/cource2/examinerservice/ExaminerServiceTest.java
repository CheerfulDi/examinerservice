package pro.sky.java.cource2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.cource2.examinerservice.exceptions.QuestionsOutOfBoundsException;
import pro.sky.java.cource2.examinerservice.model.Question;
import pro.sky.java.cource2.examinerservice.service.ExaminerServiceImpl;
import pro.sky.java.cource2.examinerservice.service.QuestionService;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.java.cource2.examinerservice.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    public static Stream<Arguments> provideParamsForTest() {
        return Stream.of(Arguments.of(DEFAULT_AMOUNT2,DEFAULT_AMOUNT3));
    }

    @Mock
    QuestionService questionServiceMock;

    @InjectMocks
    ExaminerServiceImpl out;


    @Test
    public void shouldGetQuestions() {
        Mockito.when(questionServiceMock.getRandomQuestion()).thenReturn((
                new Question(DEFAULT_QUESTION1, DEFAULT_ANSWER1)),
                new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2));

        Collection<Question> actual = out.getQuestions(DEFAULT_AMOUNT);

        Collection<Question> expected = new HashSet<>(DEFAULT_AMOUNT);
        expected.add(new Question(DEFAULT_QUESTION1, DEFAULT_ANSWER1));
        expected.add(new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2));

        assertIterableEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    public void shouldThrowQuestionOutOfBoundsException(int amount) {
        Mockito.when(questionServiceMock.getAll()).thenReturn(List.of(
                new Question(DEFAULT_QUESTION1, DEFAULT_ANSWER1),
                new Question(DEFAULT_QUESTION2, DEFAULT_ANSWER2)
        ));
        questionServiceMock.getAll();
        assertThrows(QuestionsOutOfBoundsException.class,
                ()-> out.getQuestions(amount),
                "The number of requested questions does not match the number of existing questions in the service.");
    }

}
