package pl.polsl.umpa.esp1.poolthermometer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.ComponentUrlConfiguration;
import pl.polsl.umpa.EspSetParameterRequest;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerStateNotFoundException;

import java.util.Date;

@Service
public class PoolThermometerService extends AbstractServiceComponent {
    private PoolThermometerRepository poolThermometerRepository;

    @Autowired
    public PoolThermometerService(
            PoolThermometerRepository poolThermometerRepository,
            ComponentUrlConfiguration componentUrlConfiguration
    ) {
        super(componentUrlConfiguration.getPoolThermometer());
        this.poolThermometerRepository = poolThermometerRepository;
    }

    public PoolThermometerState getPoolThermometerData() {
        return this.sendEspRequest(
                RequestType.GET, this.getComponentUrl(), null,
                PoolThermometerState.class
        );
    }

    public PoolThermometerState setPoolThermometerState(ComponentState newState) {
        return this.setParameters(super.createEspRequest(newState));
    }

    private PoolThermometerState setParameters(EspSetParameterRequest setParameterRequest) {
        return this.sendEspRequest(
                RequestType.POST, this.getComponentUrl(),
                setParameterRequest, PoolThermometerState.class
        );
    }

    private PoolThermometerState getLastPoolThermometerMeasurement() throws PoolThermometerStateNotFoundException {
        return this.poolThermometerRepository.findFirstByOrderByRecordDateDesc()
                .orElseThrow(() -> new PoolThermometerStateNotFoundException("Cannot find last pool thermometer measurement!"));
    }

    @Override
    public void onServerReset() {
        PoolThermometerState poolThermometerState;
        try {
            poolThermometerState = this.getLastPoolThermometerMeasurement();
        } catch (PoolThermometerStateNotFoundException e) {
            poolThermometerState = new PoolThermometerState(new Date());
            poolThermometerState.setState(ComponentState.OFF);
        }
    }
}
