package pl.polsl.umpa.esp3.blinds.service;

import java.util.Optional;
public interface BlindsRepository {
    Optional<Object> findFirstByOrderByRecordDateDesc();
}
