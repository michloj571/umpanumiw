package pl.polsl.umpa.esp1.pump.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.dto.PumpDataDto;

@Component
public class PumpMapper {
    public PumpDataDto mapDataToDto(PumpState pump) {
        return null;//todo implement
    }
}
