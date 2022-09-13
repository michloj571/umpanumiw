package pl.polsl.umpa.esp2.movedetector.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.AbstractRestBean;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorState;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorDataDto;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorDataReadRequest;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorSetParameterRequest;
import pl.polsl.umpa.esp2.movedetector.service.MoveDetectorService;

@RestController
@RequestMapping("/movedetector")
public class MoveDetectorRestBean extends AbstractRestBean {
    private MoveDetectorService moveDetectorService;
    private MoveDetectorMapper moveDetectorMapper;

    @Autowired
    public MoveDetectorRestBean(MoveDetectorService moveDetectorService, MoveDetectorMapper moveDetectorMapper){
        super(LoggerFactory.getLogger(MoveDetectorService.class));
        this.moveDetectorService = moveDetectorService;
        this.moveDetectorMapper = moveDetectorMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MoveDetectorDataDto> readMoveDetectorData(@RequestBody MoveDetectorDataReadRequest moveDetectorDataReadRequest){
        MoveDetectorState moveDetector = this.moveDetectorService.getMoveDetectorData();
        return ResponseEntity.status(HttpStatus.OK).body(this.moveDetectorMapper.mapDataToDto(moveDetector));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/move")
    public ResponseEntity<MoveDetectorDataDto> setMoveDetectorParameter(@RequestBody MoveDetectorSetParameterRequest moveDetectorSetParameterRequest) {
        MoveDetectorState currentState = this.moveDetectorService.setMoveDetectorParameters(moveDetectorSetParameterRequest);
        return ResponseEntity.status(HttpStatus.OK).body(this.moveDetectorMapper.mapDataToDto(currentState));
    }


}
