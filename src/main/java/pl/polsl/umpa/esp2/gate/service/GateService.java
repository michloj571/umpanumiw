package pl.polsl.umpa.esp2.gate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp2.gate.Gate;

@Service
public class GateService extends AbstractServiceComponent {

    @Autowired
    public GateService() {
    }

    public Gate getGateData(String gateURL) {
        return this.sendEspRequest(
                gateURL, null,
                Gate.class, RequestType.GET
        );
    }
}
