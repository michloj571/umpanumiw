package pl.polsl.umpa.esp1.pump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.PumpStateNotFoundException;
import pl.polsl.umpa.esp1.pump.dto.EspPumpSetParameterRequest;
import pl.polsl.umpa.esp1.pump.dto.PumpSetParameterRequest;

import java.util.Date;

@Service
public class PumpService extends AbstractServiceComponent {
    private PumpRepository pumpRepository;

    @Autowired
    public PumpService(PumpRepository pumpRepository) {
        super("jakis url kij wie cos sie zrobi");
        this.pumpRepository = pumpRepository;
    }

    public PumpState getPumpData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, PumpState.class
        );
    }

    public PumpState setPumpParameters(PumpSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private PumpState setParameters(EspPumpSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, PumpState.class
        );
    }

    private PumpState getLastPumpState() throws PumpStateNotFoundException {
        return this.pumpRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new PumpStateNotFoundException("Cannot find last pump state!"));
    }

    private EspPumpSetParameterRequest mapFromRestRequest(PumpSetParameterRequest pumpSetParameterRequest) {
        return new EspPumpSetParameterRequest(pumpSetParameterRequest.componentState());
    }

    @Override
    public void onServerReset() {
        PumpState pumpState;
        try {
            pumpState = this.getLastPumpState();
        } catch (PumpStateNotFoundException e) {
            pumpState = new PumpState(new Date());
            pumpState.setState(ComponentState.OFF);
            this.setParameters(new EspPumpSetParameterRequest(ComponentState.OFF));
            this.pumpRepository.save(pumpState);
        }
    }
}
