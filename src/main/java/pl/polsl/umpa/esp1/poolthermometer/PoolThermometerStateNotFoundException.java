package pl.polsl.umpa.esp1.poolthermometer;

import pl.polsl.umpa.RecordNotFoundException;

public class PoolThermometerStateNotFoundException extends RecordNotFoundException {
    public PoolThermometerStateNotFoundException(String message) {
        super(message);
    }
}
