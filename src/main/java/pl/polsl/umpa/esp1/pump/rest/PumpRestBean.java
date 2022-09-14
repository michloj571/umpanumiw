package pl.polsl.umpa.esp1.pump.rest;

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
import pl.polsl.umpa.SmartHomeComponentStateDto;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.service.PumpService;

@RestController
@RequestMapping("/pump")
public class PumpRestBean extends AbstractRestBean {
    private PumpService pumpService;
    private PumpMapper pumpMapper;

    @Autowired
    public PumpRestBean(PumpService pumpService, PumpMapper pumpMapper) {
        super(LoggerFactory.getLogger(PumpService.class));
        this.pumpService = pumpService;
        this.pumpMapper = pumpMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> readPumpData() {
        PumpState pump = this.pumpService.getPumpData();
        return ResponseEntity.status(HttpStatus.OK).body(super.generateReport("Pump", pump.getState()));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{newState}")
    public ResponseEntity<SmartHomeComponentStateDto> setPumpComponentState(@PathVariable("newState") String newState) {
        PumpState currentState = this.pumpService.setPumpState(ComponentState.valueOf(newState.toUpperCase()));
        return ResponseEntity.status(HttpStatus.OK).body(this.pumpMapper.mapPumpState(currentState));
    }

}
