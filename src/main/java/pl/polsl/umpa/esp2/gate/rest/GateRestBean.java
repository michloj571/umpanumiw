package pl.polsl.umpa.esp2.gate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    
}
