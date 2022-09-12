package pl.polsl.umpa.esp1.sprinkler;

import org.springframework.data.annotation.Id;
import pl.polsl.umpa.AbstractSmartHomeComponentState.WeekDay;

public class SprinklerConfiguration {
    @Id
    private String id;
    private WeekDay weekDay;
    private float start;
    private float end;

    private SprinklerConfiguration(WeekDay weekDay, float start, float end) {
        this.weekDay = weekDay;
        this.start = start;
        this.end = end;
    }

    public static SprinklerConfiguration onMonday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.MONDAY, start, end);
    }
    public static SprinklerConfiguration onTuesday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.TUESDAY, start, end);
    }
    public static SprinklerConfiguration onWednesday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.WEDNESDAY, start, end);
    }
    public static SprinklerConfiguration onThursday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.THURSDAY, start, end);
    }
    public static SprinklerConfiguration onFriday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.FRIDAY, start, end);
    }
    public static SprinklerConfiguration onSaturday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.SATURDAY, start, end);
    }
    public static SprinklerConfiguration onSunday(float start, float end) {
        return new SprinklerConfiguration(WeekDay.SUNDAY, start, end);
    }


}
