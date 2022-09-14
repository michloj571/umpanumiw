package pl.polsl.umpa;

import java.util.Date;

public class AbstractThermometerComponentState extends AbstractSmartHomeComponentState {
    private int temperature;
    private Unit unit;
    public AbstractThermometerComponentState(Date recordDate) {
        super(recordDate);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
