package App.exception;

import App.Error;

public class WithdrawalException extends RuntimeException{
    private String message;
    public WithdrawalException(String message) {
        this.message = message;
    }
}
