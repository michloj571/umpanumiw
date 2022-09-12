package pl.polsl.umpa.esp1.poolthermometer.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
interface PoolThermometerRepository extends MongoRepository<PoolThermometerState, String> {
    List<PoolThermometerState> findThermometerDegreesByRecordDateBetween(Date start, Date end);

    Optional<PoolThermometerState> findFirstByOrderByRecordDateDesc();
}
