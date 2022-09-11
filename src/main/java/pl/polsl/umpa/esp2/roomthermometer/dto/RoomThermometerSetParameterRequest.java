package pl.polsl.umpa.esp2.roomthermometer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record RoomThermometerSetParameterRequest() {
}
