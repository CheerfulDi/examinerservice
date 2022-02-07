package pro.sky.java.cource2.examinerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionExistsException extends RuntimeException{
    public QuestionExistsException() {
        super("The q-a pair is already exist");
    }
}
