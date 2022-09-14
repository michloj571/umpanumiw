package pl.polsl.umpa.esp2.gate.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.AbstractSmartHomeComponentMapper;
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp2.gate.GateState;

@Component
public class GateMapper extends AbstractSmartHomeComponentMapper {
    public SmartHomeComponentStateDto mapDataToDto(GateState gate) {
        return super.mapComponentState(gate, "Gate");
    }
}
