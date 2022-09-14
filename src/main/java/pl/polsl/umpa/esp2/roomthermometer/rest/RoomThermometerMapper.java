package pl.polsl.umpa.esp2.roomthermometer.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.AbstractSmartHomeComponentMapper;
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometerState;
import pl.polsl.umpa.esp2.roomthermometer.dto.RoomThermometerDataDto;

@Component
public class RoomThermometerMapper extends AbstractSmartHomeComponentMapper {
    public RoomThermometerDataDto mapDataToDto(RoomThermometerState roomThermometer) {
        SmartHomeComponentStateDto componentState = super.mapComponentState(
                roomThermometer, "Room thermometer"
        );
        return new RoomThermometerDataDto(
                componentState,
                roomThermometer.getTemperature(),
                roomThermometer.getUnit()
        );
    }
}
