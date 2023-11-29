package br.com.wkreuch.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class ExceptionResponse implements Serializable {

    private Instant data;
    private String message;
    private String details;

    public ExceptionResponse(Instant data, String message, String details) {
        this.data = data;
        this.message = message;
        this.details = details;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
