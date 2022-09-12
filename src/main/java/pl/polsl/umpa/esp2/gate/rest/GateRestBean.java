package pl.polsl.umpa.esp2.gate.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.AbstractRestBean;
import pl.polsl.umpa.esp1.pump.service.PumpService;
import pl.polsl.umpa.esp2.gate.GateState;
import pl.polsl.umpa.esp2.gate.dto.GateDataDto;
import pl.polsl.umpa.esp2.gate.dto.GateDataReadRequest;
import pl.polsl.umpa.esp2.gate.service.GateService;

@RestController
@RequestMapping("/gate")
public class GateRestBean extends AbstractRestBean {
    private GateService gateService;
    private GateMapper gateMapper;

    @Autowired
    public GateRestBean(GateService gateService, GateMapper gateMapper) {
        super(LoggerFactory.getLogger(PumpService.class));
        this.gateService = gateService;
        this.gateMapper = gateMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GateDataDto> readGateData(@RequestBody GateDataReadRequest gateDataReadRequest) {
        GateState gate = this.gateService.getGateData(gateDataReadRequest.gateURL());
        return ResponseEntity.status(HttpStatus.OK).body(this.gateMapper.mapDatatoDto(gate));
    }
}
