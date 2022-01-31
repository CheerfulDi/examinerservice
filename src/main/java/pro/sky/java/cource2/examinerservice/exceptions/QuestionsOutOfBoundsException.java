package pro.sky.java.cource2.examinerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionsOutOfBoundsException extends RuntimeException {

    public QuestionsOutOfBoundsException() {
        super ("The number of requested questions does not match the number of existing questions in the service.");
    }
}
