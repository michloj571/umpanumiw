package pl.polsl.umpa.esp1.pump;

import pl.polsl.umpa.RecordNotFoundException;

public class PumpStateNotFoundException extends RecordNotFoundException {
    public PumpStateNotFoundException(String message) {
        super(message);
    }
}
