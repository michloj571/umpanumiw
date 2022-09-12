package pl.polsl.umpa.esp1.poolthermometer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record PoolThermometerSetParameterRequest(ComponentState componentState) {
}
