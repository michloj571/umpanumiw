package pl.polsl.umpa.esp1.poolthermometer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometer;

@Service
public class PoolThermometerService extends AbstractServiceComponent {

    @Autowired
    public PoolThermometerService() {
    }

    public PoolThermometer getPoolThermometerData(String poolThermometerURL) {
        return this.sendEspRequest(
                poolThermometerURL, null,
                PoolThermometer.class, RequestType.GET
        );
    }
}
