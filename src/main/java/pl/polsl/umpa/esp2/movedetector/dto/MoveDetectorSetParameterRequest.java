package pl.polsl.umpa.esp2.movedetector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record MoveDetectorSetParameterRequest() {
}
