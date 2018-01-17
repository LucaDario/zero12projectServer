package it.lucadario.serviceapi.config;

import it.lucadario.serviceapi.entities.LightClassic;
import it.lucadario.serviceapi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by lucadario on 10/01/18.
 */
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {



        };
    }
}