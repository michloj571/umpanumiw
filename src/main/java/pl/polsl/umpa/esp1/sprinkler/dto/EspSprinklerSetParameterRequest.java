package pl.polsl.umpa.esp1.sprinkler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EspSprinklerSetParameterRequest() {
}
