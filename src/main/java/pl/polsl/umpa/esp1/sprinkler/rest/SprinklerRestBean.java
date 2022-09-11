package pl.polsl.umpa.esp1.sprinkler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.esp1.sprinkler.Sprinkler;
import pl.polsl.umpa.esp1.sprinkler.dto.SprinklerDataDto;
import pl.polsl.umpa.esp1.sprinkler.service.SprinklerService;


@RestController
@RequestMapping("/sprinkler")
public class SprinklerRestBean {

    private SprinklerMapper sprinklerMapper;
    private SprinklerService sprinklerService;

    @Autowired
    public SprinklerRestBean(SprinklerService sprinklerService){
        this.sprinklerService = sprinklerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SprinklerDataDto> postPumpData(@RequestBody SprinklerDataDto sprinklerDataDto) {
        // Pump pump = this.pumpService.getPumpData(pumpDataReadRequest.pumpURL());
        Sprinkler sprinkler = this.sprinklerService.getSprinklerData();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
