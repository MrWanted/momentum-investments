package App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvestorOrProductNotFoundException extends  RuntimeException{
    public InvestorOrProductNotFoundException(){
        super("Investor or Product not found!!");
    }
}
