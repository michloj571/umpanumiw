package pl.polsl.umpa.esp1.poolthermometer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometer;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataDto;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataReadRequest;
import pl.polsl.umpa.esp1.poolthermometer.service.PoolThermometerService;

@RestController
@RequestMapping("/poolthermometer")
public class PoolThermometerRestBean {

    private PoolThermometerService poolThermometerService;
    private PoolThermometerMapper poolThermometerMapper;

    @Autowired
    public PoolThermometerRestBean(PoolThermometerService poolThermometerService, PoolThermometerMapper poolThermometerMapper) {
        this.poolThermometerService = poolThermometerService;
        this.poolThermometerMapper = poolThermometerMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PoolThermometerDataDto> readPumpData(@RequestBody PoolThermometerDataReadRequest poolThermometerDataReadRequest) {
        PoolThermometer poolThermometer = this.poolThermometerService.getPoolThermometerData(poolThermometerDataReadRequest.poolThermometerURL());
        return ResponseEntity.status(HttpStatus.OK).body(this.poolThermometerMapper.mapDataToDto(poolThermometer));
    }

}
