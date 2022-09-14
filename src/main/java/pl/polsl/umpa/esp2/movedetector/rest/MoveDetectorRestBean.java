package pl.polsl.umpa.esp2.movedetector.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.AbstractRestBean;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorState;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorDataDto;
import pl.polsl.umpa.esp2.movedetector.service.MoveDetectorService;

@RestController
@RequestMapping("/movedetector")
public class MoveDetectorRestBean extends AbstractRestBean {
    private MoveDetectorService moveDetectorService;
    private MoveDetectorMapper moveDetectorMapper;

    @Autowired
    public MoveDetectorRestBean(MoveDetectorService moveDetectorService, MoveDetectorMapper moveDetectorMapper) {
        super(LoggerFactory.getLogger(MoveDetectorService.class));
        this.moveDetectorService = moveDetectorService;
        this.moveDetectorMapper = moveDetectorMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MoveDetectorDataDto> readMoveDetectorData() {
        MoveDetectorState moveDetector = this.moveDetectorService.getMoveDetectorData();
        return ResponseEntity.status(HttpStatus.OK).body(this.moveDetectorMapper.mapDataToDto(moveDetector));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{newState}")
    public ResponseEntity<MoveDetectorDataDto> setMoveDetectorParameter(@PathVariable("newState") String newState) {
        MoveDetectorState currentState = this.moveDetectorService.setMoveDetectorComponentState(
                ComponentState.valueOf(newState)
        );
        return ResponseEntity.status(HttpStatus.OK).body(this.moveDetectorMapper.mapDataToDto(currentState));
    }


}
