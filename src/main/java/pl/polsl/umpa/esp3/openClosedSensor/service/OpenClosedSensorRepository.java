package pl.polsl.umpa.esp3.openClosedSensor.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp3.openClosedSensor.OpenClosedSensorState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OpenClosedSensorRepository extends MongoRepository<OpenClosedSensorState, String> {

    List<OpenClosedSensorState> findOpenClosedSensorStateByRecordDateBetween(Date start, Date end);

    Optional<OpenClosedSensorState> findFirstByOrderByRecordDateDesc();
}
