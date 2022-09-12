package pl.polsl.umpa.esp2.movedetector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp2.movedetector.MoveDetector;

@Service
public class MoveDetectorService extends AbstractServiceComponent {

    @Autowired
    public MoveDetectorService() {

    }

    public MoveDetector getMoveDetectorData(String pumpURL){
        return this.sendEspRequest(RequestType.GET, pumpURL, null,
                                   MoveDetector.class);
    }

    @Override
    public void onServerReset() {

    }

}
