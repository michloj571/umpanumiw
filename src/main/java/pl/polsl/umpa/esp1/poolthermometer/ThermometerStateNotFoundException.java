package pl.polsl.umpa.esp1.poolthermometer;

import pl.polsl.umpa.RecordNotFoundException;

public class ThermometerStateNotFoundException extends RecordNotFoundException {
    public ThermometerStateNotFoundException(String message) {
        super(message);
    }
}
