package io.stuart.exceptions;

public class StartException extends RuntimeException {

    private static final long serialVersionUID = 4826662358797150642L;

    public StartException(Throwable cause) {
        super(cause);
    }

    public StartException(String message) {
        super(message);
    }

}
