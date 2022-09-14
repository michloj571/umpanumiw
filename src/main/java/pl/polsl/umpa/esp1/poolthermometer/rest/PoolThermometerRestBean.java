package pl.polsl.umpa.esp1.poolthermometer.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.AbstractRestBean;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataDto;
import pl.polsl.umpa.esp1.poolthermometer.service.PoolThermometerService;

@RestController
@RequestMapping("/poolthermometer")
public class PoolThermometerRestBean extends AbstractRestBean {

    private PoolThermometerService poolThermometerService;
    private PoolThermometerMapper poolThermometerMapper;

    @Autowired
    public PoolThermometerRestBean(PoolThermometerService poolThermometerService, PoolThermometerMapper poolThermometerMapper) {
        super(LoggerFactory.getLogger(PoolThermometerService.class));
        this.poolThermometerService = poolThermometerService;
        this.poolThermometerMapper = poolThermometerMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> readPoolThermometerData() {
        PoolThermometerState poolThermometerState = this.poolThermometerService.getPoolThermometerData();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_HTML)
                .body(super.generateReport(
                        "Pool thermometer", poolThermometerState.getState(),
                        WebPageComponent.field("Temperature", "" + poolThermometerState.getTemperature() + poolThermometerState.getUnit()),
                        WebPageComponent.field("Reading date", poolThermometerState.getRecordDate())
                ));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{newState}")
    public ResponseEntity<PoolThermometerDataDto> setPoolThermometerComponentState(@PathVariable("newState") String newState) {
        PoolThermometerState currentState = this.poolThermometerService.setPoolThermometerState(
                ComponentState.valueOf(newState.toUpperCase())
        );
        return ResponseEntity.status(HttpStatus.OK).body(this.poolThermometerMapper.mapDataToDto(currentState));
    }

}
