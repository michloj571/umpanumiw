package pl.polsl.umpa.esp1.poolthermometer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import pl.polsl.umpa.AbstractSmartHomeComponentState.Unit;
import pl.polsl.umpa.SmartHomeComponentStateDto;

@JsonInclude(Include.NON_NULL)
public record PoolThermometerDataDto(
        SmartHomeComponentStateDto componentState,
        int temperature, Unit unit
) {
}
