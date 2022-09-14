package pl.polsl.umpa.esp2.movedetector.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.polsl.umpa.esp2.movedetector.MoveDetectorState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MoveDetectorRepository  extends MongoRepository<MoveDetectorState, String> {

    List<MoveDetectorState> findMoveDetectorStatesByRecordDateBetween(Date start, Date end);

    Optional<MoveDetectorState> findFirstByOrderByRecordDateDesc();
}
