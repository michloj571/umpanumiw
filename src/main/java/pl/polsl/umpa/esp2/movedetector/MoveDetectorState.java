package pl.polsl.umpa.esp2.movedetector;

import pl.polsl.umpa.AbstractSmartHomeComponentState;

import java.util.Date;

public class MoveDetectorState extends AbstractSmartHomeComponentState {
    private boolean activated;
    public MoveDetectorState(Date recordDate) { super(recordDate); }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
