package pl.polsl.umpa.esp1.sprinkler;

import pl.polsl.umpa.RecordNotFoundException;

public class SprinklerStateNotFoundException extends RecordNotFoundException {
    public SprinklerStateNotFoundException(String message) {
        super(message);
    }
}
