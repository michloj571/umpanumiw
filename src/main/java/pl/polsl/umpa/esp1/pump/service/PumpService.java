package pl.polsl.umpa.esp1.pump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp1.pump.PumpState;

@Service
public class PumpService extends AbstractServiceComponent {

    @Autowired
    public PumpService() {
    }

    public PumpState getPumpData(String pumpURL) {
        return this.sendEspRequest(
                pumpURL, null,
                PumpState.class, RequestType.GET
        );
    }
}
