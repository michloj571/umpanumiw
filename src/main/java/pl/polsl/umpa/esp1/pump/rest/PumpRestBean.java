package pl.polsl.umpa.esp1.pump.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.dto.PumpDataDto;
import pl.polsl.umpa.esp1.pump.dto.PumpDataReadRequest;
import pl.polsl.umpa.esp1.pump.dto.PumpSetParameterRequest;
import pl.polsl.umpa.esp1.pump.service.PumpService;

@RestController
@RequestMapping("/pump")
public class PumpRestBean {
    private PumpService pumpService;
    private PumpMapper pumpMapper;

    @Autowired
    public PumpRestBean(PumpService pumpService, PumpMapper pumpMapper) {
        this.pumpService = pumpService;
        this.pumpMapper = pumpMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PumpDataDto> readPumpData(@RequestBody PumpDataReadRequest pumpDataReadRequest) {
        PumpState pump = this.pumpService.getPumpData(pumpDataReadRequest.pumpURL());
        return ResponseEntity.status(HttpStatus.OK).body(this.pumpMapper.mapDataToDto(pump));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/speed")
    public ResponseEntity<PumpDataDto> setPumpSpeed(@RequestBody PumpSetParameterRequest pumpSetParameterRequest) {

    }

}
