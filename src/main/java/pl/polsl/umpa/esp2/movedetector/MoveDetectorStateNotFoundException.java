package pl.polsl.umpa.esp2.movedetector;

import pl.polsl.umpa.RecordNotFoundException;

public class MoveDetectorStateNotFoundException extends RecordNotFoundException {
    public MoveDetectorStateNotFoundException(String message) {
        super(message);
    }
}
