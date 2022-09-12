package pl.polsl.umpa.esp1.sprinkler.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp1.sprinkler.SprinklerState;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprinklerRepository extends MongoRepository<SprinklerState, String> {

    List<SprinklerState> findSprinklerWorkingHoursByRecordDateBetween();

    Optional<SprinklerState> findFirstByOrderByRecordDateDesc();
}
