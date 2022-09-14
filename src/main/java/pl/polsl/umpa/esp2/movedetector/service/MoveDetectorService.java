package pl.polsl.umpa.esp2.movedetector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.EspSetParameterRequest;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorState;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorStateNotFoundException;

import java.util.Date;

@Service
public class MoveDetectorService extends AbstractServiceComponent {
    private MoveDetectorRepository moveDetectorRepository;

    @Autowired
    public MoveDetectorService(
            MoveDetectorRepository moveDetectorRepository,
            ComponentUrlConfiguration componentUrlConfiguration
    ) {
        super(componentUrlConfiguration.getMoveDetector());
        this.moveDetectorRepository = moveDetectorRepository;
    }

    public MoveDetectorState getMoveDetectorData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, MoveDetectorState.class
        );
    }

    public MoveDetectorState setMoveDetectorComponentState(ComponentState newState) {
        return this.setParameters(super.createEspRequest(newState));
    }

    private MoveDetectorState setParameters(EspSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, MoveDetectorState.class
        );
    }

    private MoveDetectorState getLastMoveDetectorState() throws MoveDetectorStateNotFoundException {
        return this.moveDetectorRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new MoveDetectorStateNotFoundException("Cannot find last move detector state!"));
    }

    @Override
    public void onServerReset() {// todo refactor all reset handlers
        MoveDetectorState moveDetectorState;
        try {
            moveDetectorState = this.getLastMoveDetectorState();
        } catch (MoveDetectorStateNotFoundException e) {
            ComponentState emergencyState = ComponentState.OFF;
            moveDetectorState = new MoveDetectorState(new Date());
            moveDetectorState.setState(emergencyState);
            this.setParameters(super.createEspRequest(emergencyState));
            this.moveDetectorRepository.save(moveDetectorState);
        }

    }

}
