package pl.polsl.umpa.esp1.poolthermometer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;
import pl.polsl.umpa.esp1.poolthermometer.ThermometerStateNotFoundException;
import pl.polsl.umpa.esp1.poolthermometer.dto.EspPoolThermometerSetParameterRequest;
import pl.polsl.umpa.esp1.poolthermometer.dto.PoolThermometerSetParameterRequest;

import java.util.Date;

@Service
public class PoolThermometerService extends AbstractServiceComponent {
    private PoolThermometerRepository poolThermometerRepository;

    @Autowired
    public PoolThermometerService(PoolThermometerRepository poolThermometerRepository) {
        super("jakis url kij wie cos sie zrobi");
        this.poolThermometerRepository = poolThermometerRepository;
    }

    public PoolThermometerState getPoolThermometerData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(), null,
                PoolThermometerState.class
        );
    }

    public PoolThermometerState setPoolThermometerParameters(PoolThermometerSetParameterRequest setParameterRequest) {
        return this.setParameters(this.mapFromRestRequest(setParameterRequest));
    }

    private PoolThermometerState setParameters(EspPoolThermometerSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, PoolThermometerState.class
        );
    }

    private PoolThermometerState getLastPoolThermometerMeasurement() throws ThermometerStateNotFoundException {
        return this.poolThermometerRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new ThermometerStateNotFoundException("Cannot find last pool thermometer measurement!"));
    }

    private EspPoolThermometerSetParameterRequest mapFromRestRequest(PoolThermometerSetParameterRequest poolThermometerSetParameterRequest) {
        return new EspPoolThermometerSetParameterRequest(poolThermometerSetParameterRequest.componentState());
    }

    @Override
    public void onServerReset() {
        PoolThermometerState poolThermometerState;
        try {
            poolThermometerState = this.getLastPoolThermometerMeasurement();
        } catch (ThermometerStateNotFoundException e) {
            poolThermometerState = new PoolThermometerState(new Date());
            poolThermometerState.setState(ComponentState.OFF);
        }
    }
}
