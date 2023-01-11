package App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.net.URISyntaxException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(PersonNotFoundExeption.class)
    public ProblemDetail handlePostNotFoundException(PersonNotFoundExeption e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setProperty("postId",e.getId());
        //URL below hardcoded for illustration purposes
        problemDetail.setType(new URI("http://localhost:8080/rest/api/investor/problems/person-not-found"));

        return problemDetail;
    }

    @ExceptionHandler(InvestorOrProductNotFoundException.class)
    public ProblemDetail handleInvestorOrProductNotFoundException(InvestorOrProductNotFoundException e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setProperty("postId",e.getMessage());
        //URL below hardcoded for illustration purposes
        problemDetail.setType(new URI("http://localhost:8080/rest/api/investor/problems/person-not-found"));

        return problemDetail;
    }

    @ExceptionHandler(WithdrawalException.class)
    public ProblemDetail handleWithdrawalException(WithdrawalException e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setProperty("postId",e.getMessage());
        //URL below hardcoded for illustration purposes
        problemDetail.setType(new URI("http://localhost:8080/rest/api/investor/problems/person-not-found"));

        return problemDetail;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleAllUncaughtException(Exception exception, WebRequest request) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,exception.getMessage());
        problemDetail.setProperty("postId",exception.getMessage());
        //URL below hardcoded for illustration purposes
        problemDetail.setType(new URI("http://localhost:8080/rest/api/investor/problems/person-not-found"));

        return problemDetail;
    }


}
