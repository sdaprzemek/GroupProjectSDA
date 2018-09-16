package pl.sda.serwer.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST)
public class ElementWithSuchIdExistException extends RuntimeException {
    public ElementWithSuchIdExistException(String message) {
        super(message);
    }
}
