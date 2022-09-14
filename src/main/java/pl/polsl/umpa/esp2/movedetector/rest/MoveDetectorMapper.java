package pl.polsl.umpa.esp2.movedetector.rest;

import org.springframework.stereotype.Component;
import pl.polsl.umpa.AbstractSmartHomeComponentMapper;
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorState;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorDataDto;

@Component
public class MoveDetectorMapper extends AbstractSmartHomeComponentMapper {
    public MoveDetectorDataDto mapDataToDto(MoveDetectorState moveDetector) {
        SmartHomeComponentStateDto componentState = super.mapComponentState(
                moveDetector, "Move detector"
        );
        return new MoveDetectorDataDto(componentState, moveDetector.isActivated());
    }
}
