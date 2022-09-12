package pl.polsl.umpa.esp3.blinds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.PumpStateNotFoundException;
import pl.polsl.umpa.esp1.pump.dto.EspPumpSetParameterRequest;
import pl.polsl.umpa.esp1.pump.dto.PumpSetParameterRequest;
import pl.polsl.umpa.esp3.blinds.Blinds;
import pl.polsl.umpa.esp3.blinds.BlindsStateNotFoundException;
import pl.polsl.umpa.esp3.blinds.dto.BlindsSetParameterRequest;
import pl.polsl.umpa.esp3.blinds.dto.EspBlindsSetParameterRequest;

import java.util.Date;

@Service
public class BlindsService extends AbstractServiceComponent {
    private BlindsRepository blindsRepository;

    @Autowired
    public BlindsService(BlindsRepository blindsRepository) {
        super("jakis url kij wie cos sie zrobi");
        this.blindsRepository = blindsRepository;
    }

    public Blinds getBlindsData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(), null,
                Blinds.class
        );
    }

    public BlindsState setBlindsParameters(BlindsSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private BlindsState setParameters(EspBlindsSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, PumpState.class
        );
    }

    private BlindsState getLastPumpState() throws BlindsStateNotFoundException {
        return this.blindsRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new BlindsStateNotFoundException("Cannot find last blinds state!"));
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
            pumpState.setState(AbstractSmartHomeComponentState.ComponentState.OFF);
            this.setParameters(new EspPumpSetParameterRequest(AbstractSmartHomeComponentState.ComponentState.OFF));
            this.pumpRepository.save(pumpState);
        }
    }
}
