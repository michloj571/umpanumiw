package pl.polsl.umpa.esp1.sprinkler.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.AbstractSmartHomeComponentMapper;
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp1.sprinkler.SprinklerState;

@Component
public class SprinklerMapper extends AbstractSmartHomeComponentMapper {
    public SmartHomeComponentStateDto mapDataToDto(SprinklerState sprinkler) {
        return super.mapComponentState(sprinkler, "Sprinkler");
    }
}
