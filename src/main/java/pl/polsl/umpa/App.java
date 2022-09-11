package pl.polsl.umpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.polsl.umpa.AbstractSmartHomeComponent.State;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.service.PumpRepository;
import pl.polsl.umpa.esp1.pump.service.PumpService;

import java.util.Date;

@SpringBootApplication
@EnableMongoRepositories
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);

    }

    public void test() {
        PumpState pumpState = new PumpState(new Date());
        pumpState.setState(State.ON);
        this.pumpRepository.save(pumpState);

    }
}
