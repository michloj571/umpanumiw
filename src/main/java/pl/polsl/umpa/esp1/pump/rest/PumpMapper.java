package pl.polsl.umpa.esp1.pump.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.AbstractSmartHomeComponentMapper;
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp1.pump.PumpState;

@Component
public class PumpMapper extends AbstractSmartHomeComponentMapper {
    public SmartHomeComponentStateDto mapPumpState(PumpState pumpState) {
        return super.mapComponentState(pumpState, "Pump");
    }
}
