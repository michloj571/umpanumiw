package pl.polsl.umpa.esp2.movedetector.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp2.movedetector.MoveDetector;

public class MoveDetectorService extends AbstractServiceComponent {

    @Autowired
    public MoveDetectorService() {

    }

    public MoveDetector getMoveDetectorData(String pumpURL){
        return this.sendEspRequest(pumpURL, null,
                                   MoveDetector.class, RequestType.GET);
    }

}
