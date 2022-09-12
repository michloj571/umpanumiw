package pl.polsl.umpa.esp1.sprinkler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.polsl.umpa.AbstractSmartHomeComponentState;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EspSprinklerSetParameterRequest(AbstractSmartHomeComponentState.ComponentState newComponentState) {
}
