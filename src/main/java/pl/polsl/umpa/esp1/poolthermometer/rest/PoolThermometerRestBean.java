package pl.polsl.umpa.esp1.poolthermometer.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.AbstractRestBean;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataDto;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataReadRequest;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerSetParameterRequest;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PoolThermometerDataDto> readPumpData() {
        PoolThermometerState poolThermometerState = this.poolThermometerService.getPoolThermometerData();
        return ResponseEntity.status(HttpStatus.OK).body(this.poolThermometerMapper.mapDataToDto(poolThermometerState));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/temperature")
    public ResponseEntity<PoolThermometerDataDto> setPoolThermometerParameter(@RequestBody PoolThermometerSetParameterRequest poolThermometerSetParameterRequest) {
        PoolThermometerState currentState = this.poolThermometerService.setPoolThermometerParameters(poolThermometerSetParameterRequest);
        return ResponseEntity.status(HttpStatus.OK).body(this.poolThermometerMapper.mapDataToDto(currentState));
    }

}
