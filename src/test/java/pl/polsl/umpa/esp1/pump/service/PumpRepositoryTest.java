package pl.polsl.umpa.esp1.pump.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractSmartHomeComponent;
import pl.polsl.umpa.esp1.pump.PumpState;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Component
public class PumpRepositoryTest {
    private PumpRepository pumpRepository;

    @Autowired
    public PumpRepositoryTest(PumpRepository pumpRepository) {
        this.pumpRepository = pumpRepository;
    }

    @Test
    public void test() {
        Date hourAgo = Date.from(Instant.now().minus(Duration.ofHours(1)));
        Date now = new Date();
        Date hourLater = Date.from(Instant.now().plus(Duration.ofHours(1)));
        PumpState pumpState = new PumpState(now);
        pumpState.setState(AbstractSmartHomeComponent.State.ON);
        this.pumpRepository.save(pumpState);
        List<PumpState> pumpStates = this.pumpRepository.findPumpStatesByRecordDateBetween(hourAgo, hourLater);

        assert !pumpStates.isEmpty();
    }
}