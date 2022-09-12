package pl.polsl.umpa.esp1.sprinkler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp1.sprinkler.SprinklerState;
import pl.polsl.umpa.esp1.sprinkler.SprinklerStateNotFoundException;
import pl.polsl.umpa.esp1.sprinkler.dto.EspSprinklerSetParameterRequest;

import java.util.Date;

@Service
public class SprinklerService extends AbstractServiceComponent {
    private SprinklerRepository sprinklerRepository;

    @Autowired
    public SprinklerService(SprinklerRepository sprinklerRepository) {
        super("Elo");
        this.sprinklerRepository = sprinklerRepository;
    }

    private SprinklerState setParameters(EspSprinklerSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, SprinklerState.class
        );
    }

    private SprinklerState getLastSprinklerState() throws SprinklerStateNotFoundException {
        return this.sprinklerRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new SprinklerStateNotFoundException("Cannot find last pump state!"));
    }

    public SprinklerState getSprinklerData() {
        return this.sendEspRequest(RequestType.GET, this.getComponentUrl(), null, SprinklerState.class);
    }

    @Override
    public void onServerReset() {
        SprinklerState sprinklerState;
        try {
            sprinklerState = this.getLastSprinklerState();
        } catch (SprinklerStateNotFoundException e) {
            sprinklerState = new SprinklerState(new Date());
            sprinklerState.setState(ComponentState.OFF);
            this.setParameters(new EspSprinklerSetParameterRequest(ComponentState.OFF));
            this.sprinklerRepository.save(sprinklerState);
        }
    }

}
