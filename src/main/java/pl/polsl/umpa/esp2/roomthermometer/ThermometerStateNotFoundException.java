package pl.polsl.umpa.esp2.roomthermometer;

import pl.polsl.umpa.RecordNotFoundException;

public class ThermometerStateNotFoundException extends RecordNotFoundException {
    public ThermometerStateNotFoundException(String message) {
        super(message);
    }
}
