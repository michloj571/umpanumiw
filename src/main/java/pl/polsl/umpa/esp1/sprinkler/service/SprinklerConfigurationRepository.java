package pl.polsl.umpa.esp1.sprinkler.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.AbstractSmartHomeComponentState.WeekDay;
import pl.polsl.umpa.esp1.sprinkler.SprinklerConfiguration;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprinklerConfigurationRepository extends MongoRepository<SprinklerConfiguration, String> {
    Optional<SprinklerConfiguration> findSprinklerConfigurationByWeekDay(WeekDay weekDay);
}
