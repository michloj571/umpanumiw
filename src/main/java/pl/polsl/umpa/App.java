package pl.polsl.umpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.polsl.umpa.AbstractSmartHomeComponent.State;
import pl.polsl.umpa.esp1.pump.PumpState;
import pl.polsl.umpa.esp1.pump.service.PumpRepository;
import pl.polsl.umpa.esp1.pump.service.PumpService;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
