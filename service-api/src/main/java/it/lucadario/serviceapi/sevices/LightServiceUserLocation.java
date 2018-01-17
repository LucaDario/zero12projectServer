package it.lucadario.serviceapi.sevices;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucadario on 10/01/18.
 */
@RestController
@RequestMapping("/light-service/{userId}/{location}")
public class LightServiceUserLocation {

    @RequestMapping(method = RequestMethod.GET)
    boolean getStatusLocation(@PathVariable String userId, @PathVariable String location){
        return true;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{status}")
    boolean setStatus(@PathVariable String userId, @PathVariable String location,
                      @PathVariable boolean status){

        return true;
    }




}
