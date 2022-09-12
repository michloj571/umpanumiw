package pl.polsl.umpa;

import org.springframework.http.HttpStatus;

public class ComponentCommunicationFailedException extends MinorServerException{
    public ComponentCommunicationFailedException(String message, HttpStatus responseStatus) {
        super(message, responseStatus);
    }
}
