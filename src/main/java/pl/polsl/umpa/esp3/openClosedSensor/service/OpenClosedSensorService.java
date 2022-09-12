package pl.polsl.umpa.esp3.openClosedSensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp3.openClosedSensor.OpenClosedSensor;

@Service
public class OpenClosedSensorService extends AbstractServiceComponent {

    @Autowired
    public OpenClosedSensorService() {
    }

    public OpenClosedSensor getOpenClosedSensorData(String openClosedSensorURL) {
        return this.sendEspRequest(
                RequestType.GET, openClosedSensorURL, null,
                OpenClosedSensor.class
        );
    }

    @Override
    public void onServerReset() {

    }
}
