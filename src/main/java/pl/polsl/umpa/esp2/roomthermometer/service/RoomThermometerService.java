package pl.polsl.umpa.esp2.roomthermometer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometer;

@Service
public class RoomThermometerService extends AbstractServiceComponent {

    @Autowired
    public RoomThermometerService() {
    }

    public RoomThermometer getPoolThermometerData(String roomThermometerURL) {
        return this.sendEspRequest(
                RequestType.GET, roomThermometerURL, null,
                RoomThermometer.class
        );
    }
    
    @Override
    public void onServerReset() {

    }
}
