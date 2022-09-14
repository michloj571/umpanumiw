package pl.polsl.umpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

import java.util.Date;

@JsonInclude
public record SmartHomeComponentStateDto(String componentName, ComponentState componentState, Date recordDate) {
}
