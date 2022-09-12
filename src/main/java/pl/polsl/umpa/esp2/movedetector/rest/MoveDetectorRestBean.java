package pl.polsl.umpa.esp2.movedetector.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.esp2.movedetector.MoveDetector;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorDataDto;
import pl.polsl.umpa.esp2.movedetector.dto.MoveDetectorDataReadRequest;
import pl.polsl.umpa.esp2.movedetector.service.MoveDetectorService;

@RestController
@RequestMapping("/movedetector")
public class MoveDetectorRestBean {
    private MoveDetectorService moveDetectorService;
    private MoveDetectorMapper moveDetectorMapper;

    @Autowired
    public MoveDetectorRestBean(MoveDetectorService moveDetectorService, MoveDetectorMapper moveDetectorMapper){
        this.moveDetectorService = moveDetectorService;
        this.moveDetectorMapper = moveDetectorMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MoveDetectorDataDto> readMoveDetectorData(@RequestBody MoveDetectorDataReadRequest moveDetectorDataReadRequest){
        MoveDetector moveDetector = this.moveDetectorService.getMoveDetectorData(moveDetectorDataReadRequest.detectorURL());
        return ResponseEntity.status(HttpStatus.OK).body(this.moveDetectorMapper.mapDataToDto(moveDetector));
    }


}
