package pl.polsl.umpa.esp1.poolthermometer.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.AbstractSmartHomeComponentMapper;
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataDto;

@Component
public class PoolThermometerMapper extends AbstractSmartHomeComponentMapper {
    public PoolThermometerDataDto mapDataToDto(PoolThermometerState poolThermometerState) {
        SmartHomeComponentStateDto componentState = super.mapComponentState(
                poolThermometerState, "Pool thermometer"
        );
        return new PoolThermometerDataDto(
                componentState,
                poolThermometerState.getTemperature(),
                poolThermometerState.getUnit()
        );
    }
}
