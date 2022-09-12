package pl.polsl.umpa.esp1.pump.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp1.pump.PumpState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
interface PumpRepository extends MongoRepository<PumpState, String> {
    List<PumpState> findPumpStatesByRecordDateBetween(Date start, Date end);

    Optional<PumpState> findFirstByOrderByRecordDateDesc();
}
