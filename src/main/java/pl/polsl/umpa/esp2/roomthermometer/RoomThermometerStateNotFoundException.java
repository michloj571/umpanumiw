package pl.polsl.umpa.esp2.roomthermometer;

import pl.polsl.umpa.RecordNotFoundException;

public class RoomThermometerStateNotFoundException extends RecordNotFoundException {
    public RoomThermometerStateNotFoundException(String message) {
        super(message);
    }
}
