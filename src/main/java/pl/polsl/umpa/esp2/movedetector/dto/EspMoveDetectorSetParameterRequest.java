package pl.polsl.umpa.esp2.movedetector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

@JsonInclude(Include.NON_NULL)
public record EspMoveDetectorSetParameterRequest(ComponentState newComponentState) {
}
