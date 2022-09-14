package pl.polsl.umpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

@JsonInclude
public record EspSetParameterRequest(ComponentState newComponentState) {
}
