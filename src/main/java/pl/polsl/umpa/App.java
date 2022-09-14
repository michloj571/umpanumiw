package pl.polsl.umpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties(ComponentUrlConfiguration.class)
@EnableMongoRepositories
public class App {//TODO finalize esp request parameters, frontend

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
