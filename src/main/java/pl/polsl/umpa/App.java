package pl.polsl.umpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
