package challenge2.com.divyansh.jsonParser.exception;

import challenge2.com.divyansh.jsonParser.entity.ErrorMessages;

public class InvalidJsonException extends Exception {
    public InvalidJsonException(String message) {
        super(String.format(message));
    }

    public InvalidJsonException() {
        super(ErrorMessages.INVALID_JSON_FORMAT);
    }
}
