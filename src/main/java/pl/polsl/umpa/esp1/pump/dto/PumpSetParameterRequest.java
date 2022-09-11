package pl.polsl.umpa.esp1.pump.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
public record PumpSetParameterRequest(int newSpeed) {
}
