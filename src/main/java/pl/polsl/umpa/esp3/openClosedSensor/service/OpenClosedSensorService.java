package pl.polsl.umpa.esp3.openClosedSensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp3.openClosedSensor.OpenClosedSensorState;
import pl.polsl.umpa.esp3.openClosedSensor.OpenClosedSensorStateNotFoundException;
import pl.polsl.umpa.esp3.openClosedSensor.dto.EspOpenClosedSensorSetParameterRequest;
import pl.polsl.umpa.esp3.openClosedSensor.dto.OpenClosedSensorSetParameterRequest;
import pl.polsl.umpa.esp3.openClosedSensor.service.OpenClosedSensorRepository;
import pl.polsl.umpa.esp3.openClosedSensor.OpenClosedSensor;

import java.util.Date;

@Service
public class OpenClosedSensorService extends AbstractServiceComponent {

    private OpenClosedSensorRepository openClosedSensorRepository;

    @Autowired
    public OpenClosedSensorService(OpenClosedSensorRepository openClosedSensorRepository) {
        super("jakis url kij wie cos sie zrobi");
        this.openClosedSensorRepository = openClosedSensorRepository;
    }

    public OpenClosedSensorState getOpenClosedSensorData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, OpenClosedSensorState.class
        );
    }

    public OpenClosedSensorState setOpenClosedSensorParameters(OpenClosedSensorSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private OpenClosedSensorState setParameters(EspOpenClosedSensorSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, OpenClosedSensorState.class
        );
    }

    private OpenClosedSensorState getLastOpenClosedSensorState() throws OpenClosedSensorStateNotFoundException {
        return this.openClosedSensorRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new OpenClosedSensorStateNotFoundException("Cannot find last openClosedSensor state!"));
    }

    private EspOpenClosedSensorSetParameterRequest mapFromRestRequest(OpenClosedSensorSetParameterRequest openClosedSensorSetParameterRequest) {
        return new EspOpenClosedSensorSetParameterRequest(openClosedSensorSetParameterRequest.componentState());
    }

    @Override
    public void onServerReset() {
        OpenClosedSensorState openClosedSensorState;
        try {
            openClosedSensorState = this.getLastOpenClosedSensorState();
        } catch (OpenClosedSensorStateNotFoundException e) {
            openClosedSensorState = new OpenClosedSensorState(new Date());
            openClosedSensorState.setState(ComponentState.OFF);
            this.setParameters(new EspOpenClosedSensorSetParameterRequest(ComponentState.OFF));
            this.openClosedSensorRepository.save(openClosedSensorState);
        }
    }
}
