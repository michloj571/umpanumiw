package pl.polsl.umpa.esp2.roomthermometer.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp2.roomthermometer.RoomThermometerState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomThermometerRepository extends MongoRepository <RoomThermometerState, String> {
    List<RoomThermometerState> findRoomThermometerStateByRecordDateBetween(Date start, Date end);

    Optional<RoomThermometerState> findFirstByOrderByRecordDateDesc();
}
