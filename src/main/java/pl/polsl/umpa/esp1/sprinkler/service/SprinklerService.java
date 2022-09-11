package pl.polsl.umpa.esp1.sprinkler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.umpa.AbstractServiceComponent;
import pl.polsl.umpa.esp1.sprinkler.Sprinkler;

@Service
public class SprinklerService extends AbstractServiceComponent {

    @Autowired
    public SprinklerService() {
    }

    public Sprinkler getSprinklerData(String sprinklerURL) {
        return this.sendEspRequest(sprinklerURL, null, Sprinkler.class, RequestType.GET);
    }

}
