package pl.polsl.umpa.esp3.blinds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp3.blinds.Blinds;

@Service
public class BlindsService extends AbstractServiceComponent {
    @Autowired
    public BlindsService() {
    }

    public Blinds getBlindsData(String blindsURL) {
        return this.sendEspRequest(
                blindsURL, null,
                Blinds.class, RequestType.GET
        );
    }
}
