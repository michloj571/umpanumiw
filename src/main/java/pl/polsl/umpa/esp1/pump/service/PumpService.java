package pl.polsl.umpa.esp1.pump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp1.pump.Pump;

@Service
public class PumpService extends AbstractServiceComponent {

    @Autowired
    public PumpService() {
    }

    public Pump getPumpData(String pumpURL) {
        return this.sendEspRequest(
                pumpURL, null,
                Pump.class, RequestType.GET
        );
    }
}
