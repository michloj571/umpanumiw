package pl.polsl.umpa.esp2.movedetector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorState;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorStateNotFoundException;
import pl.polsl.umpa.esp2.movedetector.dto.EspMoveDetectorSetParameterRequest;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorSetParameterRequest;

import java.util.Date;

@Service
public class MoveDetectorService extends AbstractServiceComponent {

    private MoveDetectorRepository moveDetectorRepository;

    @Autowired
    public MoveDetectorService(MoveDetectorRepository moveDetectorRepository) {
        super("jakis url kij wie cos sie zrobi");
        this.moveDetectorRepository = moveDetectorRepository;
    }

    public MoveDetectorState getMoveDetectorData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, MoveDetectorState.class
        );
    }

    public MoveDetectorState setMoveDetectorParameters(MoveDetectorSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private MoveDetectorState setParameters(EspMoveDetectorSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, MoveDetectorState.class
        );
    }

    private MoveDetectorState getLastMoveDetectorState() throws MoveDetectorStateNotFoundException {
        return this.moveDetectorRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new MoveDetectorStateNotFoundException("Cannot find last move detector state!"));
    }

    private EspMoveDetectorSetParameterRequest mapFromRestRequest(MoveDetectorSetParameterRequest moveDetectorSetParameterRequest) {
        return new EspMoveDetectorSetParameterRequest(moveDetectorSetParameterRequest.componentState());
    }

    @Override
    public void onServerReset() {
        MoveDetectorState moveDetectorState;
        try {
            moveDetectorState = this.getLastMoveDetectorState();
        } catch (MoveDetectorStateNotFoundException e) {
            moveDetectorState = new MoveDetectorState(new Date());
            moveDetectorState.setState(ComponentState.OFF);
            this.setParameters(new EspMoveDetectorSetParameterRequest(ComponentState.OFF));
            this.moveDetectorRepository.save(moveDetectorState);
        }
    }

}
