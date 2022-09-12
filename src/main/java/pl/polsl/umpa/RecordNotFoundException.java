package pl.polsl.umpa;

public abstract class RecordNotFoundException extends CriticalServerException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
