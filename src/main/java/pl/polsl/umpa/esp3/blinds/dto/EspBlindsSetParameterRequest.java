package pl.polsl.umpa.esp3.blinds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record EspBlindsSetParameterRequest() {
}