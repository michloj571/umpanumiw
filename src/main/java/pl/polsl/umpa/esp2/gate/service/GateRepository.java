package pl.polsl.umpa.esp2.gate.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp2.gate.GateState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GateRepository extends MongoRepository<GateState, String> {
    List<GateState> findGateStatesByRecordDateBetween(Date start, Date end);

    Optional<GateState> findFirstByOrderByRecordDateDesc();
}
