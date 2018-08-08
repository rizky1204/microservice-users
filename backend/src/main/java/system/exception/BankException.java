package system.exception;

import system.requesthandle.StatusCode;

public class BankException extends RuntimeException {

    private StatusCode code = StatusCode.ERROR;

    public BankException() {
        super();
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }



}
