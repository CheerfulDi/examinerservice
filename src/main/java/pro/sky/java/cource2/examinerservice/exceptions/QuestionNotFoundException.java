package pro.sky.java.cource2.examinerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Question is not found")
public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException() {
        super("Question is not found");
    }
}
