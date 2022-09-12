package pl.polsl.umpa.esp2.roomthermometer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometerState;
import pl.polsl.umpa.esp2.roomthermometer.ThermometerStateNotFoundException;
import pl.polsl.umpa.esp2.roomthermometer.dto.EspRoomThermometerSetParameterRequest;
import pl.polsl.umpa.esp2.roomthermometer.dto.RoomThermometerSetParameterRequest;

import java.util.Date;

@Service
public class RoomThermometerService extends AbstractServiceComponent {
    private RoomThermometerRepository roomThermometerRepository;

    @Autowired
    public RoomThermometerService(RoomThermometerRepository roomThermometerRepository) {
        super("jakis url kij wie cos sie zrobi");
        this.roomThermometerRepository = roomThermometerRepository;
    }

    public RoomThermometerState getRoomThermometerData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(),
                null, RoomThermometerState.class
        );
    }

    public RoomThermometerState setRoomThermometerParameters(RoomThermometerSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private RoomThermometerState setParameters(EspRoomThermometerSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, RoomThermometerState.class
        );
    }

    private RoomThermometerState getLastRoomThermometerMeasurement() throws ThermometerStateNotFoundException {
        return this.roomThermometerRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new ThermometerStateNotFoundException("Cannot find last room thermometer measurement!"));
    }

    private EspRoomThermometerSetParameterRequest mapFromRestRequest(RoomThermometerSetParameterRequest roomThermometerSetParameterRequest) {
        return new EspRoomThermometerSetParameterRequest(roomThermometerSetParameterRequest.componentState());
    }
    
    @Override
    public void onServerReset() {
        RoomThermometerState roomThermometerState;
        try {
            roomThermometerState = this.getLastRoomThermometerMeasurement();
        } catch (ThermometerStateNotFoundException e) {
            roomThermometerState = new RoomThermometerState(new Date());
            roomThermometerState.setState(ComponentState.OFF);
            this.setParameters(new EspRoomThermometerSetParameterRequest(ComponentState.OFF));
            this.roomThermometerRepository.save(roomThermometerState);
        }
    }
}
