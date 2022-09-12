package pl.polsl.umpa.esp1.sprinkler;

import pl.polsl.umpa.AbstractSmartHomeComponentState;

import java.util.Date;

public class SprinklerState extends AbstractSmartHomeComponentState {
    private WeekDay weekDay;
    private float start;
    private float end;

    public SprinklerState(Date recordDate) {
        super(recordDate);
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }
}
