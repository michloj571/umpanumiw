package pl.polsl.umpa.esp1.sprinkler.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.AbstractRestBean;
import pl.polsl.umpa.esp1.sprinkler.SprinklerState;
import pl.polsl.umpa.esp1.sprinkler.dto.SprinklerDataDto;
import pl.polsl.umpa.esp1.sprinkler.dto.SprinklerDataReadRequest;
import pl.polsl.umpa.esp1.sprinkler.dto.SprinklerSetParameterRequest;
import pl.polsl.umpa.esp1.sprinkler.service.SprinklerService;


@RestController
@RequestMapping("/sprinkler")
public class SprinklerRestBean extends AbstractRestBean {

    private SprinklerMapper sprinklerMapper;
    private SprinklerService sprinklerService;

    @Autowired
    public SprinklerRestBean(SprinklerService sprinklerService, SprinklerMapper sprinklerMapper){
        super(LoggerFactory.getLogger(SprinklerService.class));
        this.sprinklerService = sprinklerService;
        this.sprinklerMapper = sprinklerMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SprinklerDataDto> readPumpData(@RequestBody SprinklerDataReadRequest pumpDataReadRequest) {
        SprinklerState sprinkler = this.sprinklerService.getSprinklerData();
        return ResponseEntity.status(HttpStatus.OK).body(this.sprinklerMapper.mapDataToDto(sprinkler));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/speed")
    public ResponseEntity<SprinklerDataDto> setPumpParameter(@RequestBody SprinklerSetParameterRequest sprinklerSetParameterRequest) {
        SprinklerState currentState = this.sprinklerService.setSprinklerParameters(sprinklerSetParameterRequest);
        return ResponseEntity.status(HttpStatus.OK).body(this.sprinklerMapper.mapDataToDto(currentState));
    }
}
