package pl.polsl.umpa.esp1.sprinkler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.EspSetParameterRequest;
import pl.polsl.umpa.esp1.sprinkler.SprinklerState;
import pl.polsl.umpa.esp1.sprinkler.SprinklerStateNotFoundException;

import java.util.Date;

@Service
public class SprinklerService extends AbstractServiceComponent {
    private SprinklerRepository sprinklerRepository;

    @Autowired
    public SprinklerService(
            SprinklerRepository sprinklerRepository,
            ComponentUrlConfiguration componentUrlConfiguration
    ) {
        super(componentUrlConfiguration.getSprinkler());
        this.sprinklerRepository = sprinklerRepository;
    }

    public SprinklerState getSprinklerData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(), null,
                SprinklerState.class
        );
    }

    public SprinklerState setSprinklerState(ComponentState newState) {
        return this.setParameters(super.createEspRequest(newState));
    }

    private SprinklerState setParameters(EspSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, SprinklerState.class
        );
    }

    private SprinklerState getLastSprinklerState() throws SprinklerStateNotFoundException {
        return this.sprinklerRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new SprinklerStateNotFoundException("Cannot find last sprinkler state!"));
    }

    @Override
    public void onServerReset() {
        SprinklerState sprinklerState;
        try {
            sprinklerState = this.getLastSprinklerState();
        } catch (SprinklerStateNotFoundException e) {
            ComponentState newComponentState = ComponentState.OFF;
            sprinklerState = new SprinklerState(new Date());
            sprinklerState.setState(newComponentState);
            this.setParameters(super.createEspRequest(newComponentState));
            this.sprinklerRepository.save(sprinklerState);
        }
    }
}
