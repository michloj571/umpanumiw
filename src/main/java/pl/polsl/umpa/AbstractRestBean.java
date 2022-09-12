package pl.polsl.umpa;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AbstractRestBean {
    protected Logger logger;

    protected AbstractRestBean(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler({MinorServerException.class})
    public ResponseEntity<?> handleRuntimeException(MinorServerException e) {
        this.logger.error(e.getMessage());
        return ResponseEntity.status(e.getResponseStatus()).build();
    }
}
