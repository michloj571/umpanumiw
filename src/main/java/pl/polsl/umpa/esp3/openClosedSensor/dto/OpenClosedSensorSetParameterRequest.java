package pl.polsl.umpa.esp3.openClosedSensor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record OpenClosedSensorSetParameterRequest(int newSpeed) {
}
