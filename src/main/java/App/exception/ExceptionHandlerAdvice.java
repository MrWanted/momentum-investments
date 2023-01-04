package App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.net.URISyntaxException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(PersonNotFoundExeption.class)
    public ProblemDetail handlePostNotFoundException(PersonNotFoundExeption e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setProperty("postId",e.getId());
        problemDetail.setType(new URI("http://localhost:8080/rest/api/investor/problems/person-not-found"));

        return problemDetail;
    }

}
