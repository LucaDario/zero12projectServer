package it.lucadario.serviceapi.repositories;

import it.lucadario.serviceapi.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by lucadario on 10/01/18.
 */
public interface UserRepository extends MongoRepository<User,String> {

    User findById(String id);
}
