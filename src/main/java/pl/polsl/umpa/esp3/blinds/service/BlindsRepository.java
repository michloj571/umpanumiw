package pl.polsl.umpa.esp3.blinds.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp3.blinds.BlindsState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlindsRepository extends MongoRepository<BlindsState, String> {
    List<BlindsState> findBlindsStatesByRecordDateBetween(Date start, Date end);

    Optional<Object> findFirstByOrderByRecordDateDesc();
}
