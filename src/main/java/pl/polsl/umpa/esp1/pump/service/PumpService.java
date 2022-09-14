package pl.polsl.umpa.esp1.pump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.EspSetParameterRequest;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.PumpStateNotFoundException;

import java.util.Date;

@Service
public class PumpService extends AbstractServiceComponent {
    private PumpRepository pumpRepository;

    @Autowired
    public PumpService(PumpRepository pumpRepository, ComponentUrlConfiguration componentUrlConfiguration) {
        super(componentUrlConfiguration.getPump());
        this.pumpRepository = pumpRepository;
    }

    public PumpState getPumpData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, PumpState.class
        );
    }

    public PumpState setPumpState(ComponentState newState) {
        return this.setParameters(super.createEspRequest(newState));
    }

    private PumpState setParameters(EspSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, PumpState.class
        );
    }

    private PumpState getLastPumpState() throws PumpStateNotFoundException {
        return this.pumpRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new PumpStateNotFoundException("Cannot find last pump state!"));
    }

    @Override
    public void onServerReset() {
        PumpState pumpState;
        try {
            pumpState = this.getLastPumpState();
        } catch (PumpStateNotFoundException e) {
            pumpState = new PumpState(new Date());
            pumpState.setState(ComponentState.OFF);
            this.setParameters(super.createEspRequest(ComponentState.OFF));
            this.pumpRepository.save(pumpState);
        }
    }
}
