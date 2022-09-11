package pl.polsl.umpa;

import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class AbstractSmartHomeComponent {

    @Id
    private String id;
    private Date recordDate;
    private State state;

    protected enum State {
        ON, OFF
    }

    public AbstractSmartHomeComponent(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getId() {
        return id;
    }

    public AbstractSmartHomeComponent setId(String id) {
        this.id = id;
        return this;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public AbstractSmartHomeComponent setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
        return this;
    }

    public State getState() {
        return state;
    }

    public AbstractSmartHomeComponent setState(State state) {
        this.state = state;
        return this;
    }
}
