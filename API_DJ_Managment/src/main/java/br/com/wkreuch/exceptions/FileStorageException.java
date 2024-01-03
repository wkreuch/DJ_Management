package br.com.wkreuch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException {
    public FileStorageException(String messageError) {
        super(messageError);
    }
    public FileStorageException(String messageError, Throwable cause) {
        super(messageError, cause);
    }
}
