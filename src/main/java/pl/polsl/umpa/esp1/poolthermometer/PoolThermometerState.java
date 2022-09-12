package pl.polsl.umpa.esp1.poolthermometer;

import pl.polsl.umpa.AbstractSmartHomeComponentState;

import java.util.Date;

public class PoolThermometerState extends AbstractSmartHomeComponentState {
    private int temperature;
    private Unit unit;

    public PoolThermometerState(Date recordDate) {
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
