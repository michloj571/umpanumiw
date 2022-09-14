package pl.polsl.umpa.esp2.movedetector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.SmartHomeComponentStateDto;

import java.util.Date;

@JsonInclude(Include.NON_NULL)
public record MoveDetectorDataDto(SmartHomeComponentStateDto componentState, boolean activated) {
}
