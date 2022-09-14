package pl.polsl.umpa.esp2.gate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.EspSetParameterRequest;
import pl.polsl.umpa.esp2.gate.GateState;
import pl.polsl.umpa.esp2.gate.GateStateNotFoundException;

import java.util.Date;

@Service
public class GateService extends AbstractServiceComponent {

    private GateRepository gateRepository;

    @Autowired
    public GateService(GateRepository gateRepository, ComponentUrlConfiguration componentUrlConfiguration) {
        super(componentUrlConfiguration.getGate());
        this.gateRepository = gateRepository;
    }

    public GateState getGateData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(), null,
                GateState.class
        );
    }

    public GateState setGateComponentState(ComponentState newState) {
        return this.setParameters(super.createEspRequest(newState));
    }

    private GateState getLastGateState() throws GateStateNotFoundException {
        return this.gateRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new GateStateNotFoundException("Cannot find last gate state!"));
    }

    private GateState setParameters(EspSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, GateState.class
        );
    }

    @Override
    public void onServerReset() {
        GateState gateState;
        try {
            gateState = this.getLastGateState();
        } catch (GateStateNotFoundException e) {
            ComponentState emergencyState = ComponentState.CLOSED;
            gateState = new GateState(new Date());
            gateState.setState(emergencyState);
            this.setParameters(super.createEspRequest(emergencyState));
            this.gateRepository.save(gateState);
        }
    }
}

