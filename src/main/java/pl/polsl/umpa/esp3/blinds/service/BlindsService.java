package pl.polsl.umpa.esp3.blinds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.esp3.blinds.BlindsState;
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

    public BlindsState getBlindsData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(), null,
                BlindsState.class
        );
    }

    public BlindsState setBlindsParameters(BlindsSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private BlindsState setParameters(EspBlindsSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, BlindsState.class
        );
    }

    private BlindsState getLastBlindsState() throws BlindsStateNotFoundException {
        return this.blindsRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new BlindsStateNotFoundException("Cannot find last blinds state!"));
    }

    private EspBlindsSetParameterRequest mapFromRestRequest(BlindsSetParameterRequest blindsSetParameterRequest) {
        return new EspBlindsSetParameterRequest(blindsSetParameterRequest.componentState());
    }

    @Override
    public void onServerReset() {
        BlindsState blindsState;
        try {
            blindsState = this.getLastBlindsState();
        } catch (BlindsStateNotFoundException e) {
            blindsState = new BlindsState(new Date());
            blindsState.setState(AbstractSmartHomeComponentState.ComponentState.OFF);
            this.setParameters(new EspBlindsSetParameterRequest(AbstractSmartHomeComponentState.ComponentState.OFF));
            this.blindsRepository.save(blindsState);
        }
    }
}
