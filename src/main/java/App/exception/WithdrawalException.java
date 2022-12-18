package App.exception;

import App.Error;

public class WithdrawalException extends RuntimeException{
    public WithdrawalException(Error error) {
    }
}
