package pl.polsl.umpa.esp3.openClosedSensor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

@JsonInclude(Include.NON_NULL)
public record EspOpenClosedSensorSetParameterRequest(ComponentState newComponentState) {
}
