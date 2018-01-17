package it.lucadario.serviceapi.sevices;

import it.lucadario.serviceapi.entities.User;
import it.lucadario.serviceapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lucadario on 10/01/18.
 */
@RestController
@RequestMapping("/light-service/{userId}")
public class LightServiceUser {


    @Autowired
    private UserRepository userRepository;


   /* @RequestMapping(method = RequestMethod.GET)
    String getConsumption(@PathVariable String userId){
        return String.valueOf(150);
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public List<User> addLight(){
        return userRepository.findAll();
    }



}