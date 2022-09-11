package pl.polsl.umpa.esp2.gate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.esp2.gate.Gate;
import pl.polsl.umpa.esp2.gate.dto.GateDataDto;
import pl.polsl.umpa.esp2.gate.dto.GateDataReadRequest;
import pl.polsl.umpa.esp2.gate.service.GateService;

@RestController
@RequestMapping("/gate")
public class GateRestBean {
    private GateService gateService;
    private GateMapper gateMapper;

    @Autowired
    public GateRestBean(GateService gateService, GateMapper gateMapper) {
        this.gateService = gateService;
        this.gateMapper = gateMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GateDataDto> readGateData(@RequestBody GateDataReadRequest gateDataReadRequest) {
        Gate gate = this.gateService.getGateData(gateDataReadRequest.gateURL());
        return ResponseEntity.status(HttpStatus.OK).body(this.gateMapper.mapDatatoDto(gate));
    }
}
