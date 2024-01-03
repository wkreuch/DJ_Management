package br.com.wkreuch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String messageError) {
        super(messageError);
    }
    public MyFileNotFoundException(String messageError, Throwable cause) {
        super(messageError, cause);
    }
}
