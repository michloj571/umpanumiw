package pl.polsl.umpa.esp2.roomthermometer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerDataReadRequest;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometer;
import pl.polsl.umpa.esp2.roomthermometer.dto.RoomThermometerDataDto;
import pl.polsl.umpa.esp2.roomthermometer.service.RoomThermometerService;

;

@RestController
@RequestMapping("/roomthermometer")
public class RoomThermometerRestBean {

    private RoomThermometerService roomThermometerService;
    private RoomThermometerMapper roomThermometerMapper;

    @Autowired
    public RoomThermometerRestBean(RoomThermometerService roomThermometerService, RoomThermometerMapper poolThermometerMapper) {
        this.roomThermometerService = roomThermometerService;
        this.roomThermometerMapper = poolThermometerMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RoomThermometerDataDto> readPumpData(@RequestBody PoolThermometerDataReadRequest roomThermometerDataReadRequest) {
        RoomThermometer roomThermometer = this.roomThermometerService.getPoolThermometerData(roomThermometerDataReadRequest.poolThermometerURL());
        return ResponseEntity.status(HttpStatus.OK).body(this.roomThermometerMapper.mapDataToDto(roomThermometer));
    }
}
