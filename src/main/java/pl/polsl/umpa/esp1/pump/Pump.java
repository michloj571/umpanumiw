package pl.polsl.umpa.esp1.pump;

import pl.polsl.umpa.AbstractSmartHomeComponent;

import java.util.Date;

public class Pump extends AbstractSmartHomeComponent {

    public Pump(Date recordDate) {
        super(recordDate);
    }

    public enum PumpSpeedUnit{
        LITERS_PER_MINUTE
    }
}
