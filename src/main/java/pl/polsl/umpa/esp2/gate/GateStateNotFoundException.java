package pl.polsl.umpa.esp2.gate;

import pl.polsl.umpa.RecordNotFoundException;

public class GateStateNotFoundException extends RecordNotFoundException {
    public GateStateNotFoundException(String msg){ super(msg); }
}
