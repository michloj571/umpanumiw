package pl.polsl.umpa.esp1.poolthermometer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

@JsonInclude(Include.NON_NULL)
public record EspPoolThermometerSetParameterRequest(ComponentState newComponentState) {
}
