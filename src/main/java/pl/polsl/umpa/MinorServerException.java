package pl.polsl.umpa;

import org.springframework.http.HttpStatus;

public class MinorServerException extends RuntimeException {
    private HttpStatus responseStatus;

    public MinorServerException(String message, HttpStatus responseStatus){
        super(message);
        this.responseStatus = responseStatus;
    }

    public HttpStatus getResponseStatus() {
        return this.responseStatus;
    }
}
