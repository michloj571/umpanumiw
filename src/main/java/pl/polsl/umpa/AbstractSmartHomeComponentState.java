package pl.polsl.umpa;

import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class AbstractSmartHomeComponentState {

    @Id
    private String id;
    private Date recordDate;
    private ComponentState componentState;

    public enum ComponentState {
        ON,
        OFF,
        OPENED,
        CLOSED

    }

    public enum Unit {
        CELSIUS {
            @Override
            public String toString() {
                return "°C";
            }
        }, FAHRENHEIT {
            @Override
            public String toString() {
                return "°F";
            }
        };
    }

    public enum WeekDay {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public AbstractSmartHomeComponentState(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getId() {
        return id;
    }

    public AbstractSmartHomeComponentState setId(String id) {
        this.id = id;
        return this;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public AbstractSmartHomeComponentState setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
        return this;
    }

    public ComponentState getState() {
        return componentState;
    }

    public AbstractSmartHomeComponentState setState(ComponentState componentState) {
        this.componentState = componentState;
        return this;
    }
}
