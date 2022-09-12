package pl.polsl.umpa.esp2.gate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState;
import pl.polsl.umpa.esp2.gate.GateState;
import pl.polsl.umpa.esp2.gate.GateStateNotFoundException;
import pl.polsl.umpa.esp2.gate.dto.EspGateSetParameterRequest;

import java.util.Date;

@Service
public class GateService extends AbstractServiceComponent {

    private GateRepository gateRepository;
    @Autowired
    public GateService(GateRepository gateRepository) {
        super("Gate here");
        this.gateRepository = gateRepository;
    }

    public GateState getGateData(String gateURL) {
        return this.sendEspRequest(
                RequestType.GET, gateURL, null,
                GateState.class
        );
    }
    private GateState setParameters(EspGateSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, GateState.class
        );
    }

    public GateState getLastGateState() throws GateStateNotFoundException {
        return this.gateRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new GateStateNotFoundException("Cannot find last gate state!"));
    }

    @Override
    public void onServerReset() {
        GateState gateState;
        try {
            gateState = this.getLastGateState();
        } catch (GateStateNotFoundException e) {
            gateState = new GateState(new Date());
            gateState.setState(AbstractSmartHomeComponentState.ComponentState.OFF);
            this.setParameters(new EspGateSetParameterRequest(AbstractSmartHomeComponentState.ComponentState.OFF));
            this.gateRepository.save(gateState);
        }
    }
}

