package pl.polsl.umpa.esp3.openClosedSensor;

import org.springframework.data.annotation.Transient;
import pl.polsl.umpa.AbstractSmartHomeComponent;

import java.util.Date;

public class OpenClosedSensor extends AbstractSmartHomeComponent {
    public OpenClosedSensor(Date recordDate) {
        super(recordDate);
    }
}
