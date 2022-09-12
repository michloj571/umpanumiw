package pl.polsl.umpa.esp3.blinds.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.AbstractSmartHomeComponentState;
import pl.polsl.umpa.esp3.blinds.BlindsConfiguration;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlindsConfigurationRepository  extends MongoRepository<BlindsConfiguration, String> {
    List<BlindsConfiguration> findBlindsConfiguration();

    Optional<BlindsConfiguration> findBlindsConfigurationByWeekDay(AbstractSmartHomeComponentState.WeekDay weekDay);
}
