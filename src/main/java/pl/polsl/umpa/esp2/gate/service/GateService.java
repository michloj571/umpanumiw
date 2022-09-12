package pl.polsl.umpa.esp2.gate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp2.gate.Gate;
import pl.polsl.umpa.esp2.gate.GateState;
import pl.polsl.umpa.esp2.gate.GateStateNotFoundException;

import java.util.Date;

@Service
public class GateService extends AbstractServiceComponent {

    private GateRepository gateRepository;
    @Autowired
    public GateService(GateRepository gateRepository) {
        super("Gate here");
        this.gateRepository = gateRepository;
    }

    public Gate getGateData(String gateURL) {
        return this.sendEspRequest(
                RequestType.GET, gateURL, null,
                Gate.class
        );
    }

    public GateState getLastGateState() throws GateStateNotFoundException {
        return this.gateRepository
    }
    @Override
    public void onServerReset() {

    }
}
