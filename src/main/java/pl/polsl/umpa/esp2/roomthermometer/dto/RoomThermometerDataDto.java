package pl.polsl.umpa.esp2.roomthermometer.dto;

import pl.polsl.umpa.AbstractSmartHomeComponentState.Unit;
import pl.polsl.umpa.SmartHomeComponentStateDto;

public record RoomThermometerDataDto(
        SmartHomeComponentStateDto componentState,
        int temperature, Unit unit
) {
}
