package pl.polsl.umpa.esp3.blinds;

import org.springframework.data.annotation.Id;
import pl.polsl.umpa.AbstractSmartHomeComponentState;
import pl.polsl.umpa.AbstractSmartHomeComponentState.WeekDay;


public class BlindsConfiguration {
    @Id
    private String id;
    private AbstractSmartHomeComponentState.WeekDay weekDay;
    private float start;
    private float end;

    private BlindsConfiguration(WeekDay weekDay, float start, float end) {
        this.weekDay = weekDay;
        this.start = start;
        this.end = end;
    }

    public static BlindsConfiguration onMonday(float start, float end) {
        return new BlindsConfiguration(WeekDay.MONDAY, start, end);
    }
    public static BlindsConfiguration onTuesday(float start, float end) {
        return new BlindsConfiguration(WeekDay.TUESDAY, start, end);
    }
    public static BlindsConfiguration onWednesday(float start, float end) {
        return new BlindsConfiguration(WeekDay.WEDNESDAY, start, end);
    }
    public static BlindsConfiguration onThursday(float start, float end) {
        return new BlindsConfiguration(WeekDay.THURSDAY, start, end);
    }
    public static BlindsConfiguration onFriday(float start, float end) {
        return new BlindsConfiguration(WeekDay.FRIDAY, start, end);
    }
    public static BlindsConfiguration onSaturday(float start, float end) {
        return new BlindsConfiguration(WeekDay.SATURDAY, start, end);
    }
    public static BlindsConfiguration onSunday(float start, float end) {
        return new BlindsConfiguration(WeekDay.SUNDAY, start, end);
    }
}
