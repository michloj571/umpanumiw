package pl.polsl.umpa.esp2.roomthermometer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.EspSetParameterRequest;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometerState;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometerStateNotFoundException;

import java.util.Date;

@Service
public class RoomThermometerService extends AbstractServiceComponent {
    private RoomThermometerRepository roomThermometerRepository;

    @Autowired
    public RoomThermometerService(
            RoomThermometerRepository roomThermometerRepository,
            ComponentUrlConfiguration componentUrlConfiguration
    ) {
        super(componentUrlConfiguration.getRoomThermometer());
        this.roomThermometerRepository = roomThermometerRepository;
    }

    public RoomThermometerState getRoomThermometerData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, RoomThermometerState.class
        );
    }

    public RoomThermometerState setRoomThermometerComponentState(ComponentState newState) {
        return this.setParameters(super.createEspRequest(newState));
    }

    private RoomThermometerState setParameters(EspSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, RoomThermometerState.class
        );
    }

    private RoomThermometerState getLastRoomThermometerMeasurement() throws RoomThermometerStateNotFoundException {
        return this.roomThermometerRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new RoomThermometerStateNotFoundException("Cannot find last room thermometer measurement!"));
    }

    @Override
    public void onServerReset() {
        RoomThermometerState roomThermometerState;
        try {
            roomThermometerState = this.getLastRoomThermometerMeasurement();
        } catch (RoomThermometerStateNotFoundException e) {
            ComponentState emergencyState = ComponentState.OFF;
            roomThermometerState = new RoomThermometerState(new Date());
            roomThermometerState.setState(emergencyState);
            this.setParameters(super.createEspRequest(emergencyState));
            this.roomThermometerRepository.save(roomThermometerState);
        }
    }
}
