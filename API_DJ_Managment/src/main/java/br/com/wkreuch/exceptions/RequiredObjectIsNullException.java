package br.com.wkreuch.exceptions;

public class RequiredObjectIsNullException extends RuntimeException {
    public RequiredObjectIsNullException(String messageError) {
        super(messageError);
    }

    public RequiredObjectIsNullException() {
        super("It is not allowed to persist a null object!");
    }
}
