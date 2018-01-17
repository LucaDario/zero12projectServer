package it.lucadario.serviceapi.sevices;

import it.lucadario.lightscomponents.Light;
import it.lucadario.serviceapi.Exception.HttpUnauthorizedException;
import it.lucadario.serviceapi.Exception.IdLightNotUnique;
import it.lucadario.serviceapi.Exception.UserIsAlreadyPresent;
import it.lucadario.serviceapi.Exception.UserIsNotPresent;
import it.lucadario.serviceapi.entities.LightClassic;
import it.lucadario.serviceapi.entities.User;
import it.lucadario.serviceapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by lucadario on 11/01/18.
 */
@RestController
@RequestMapping()
public class RestControllerUserLight {

    private UserRepository userRepository;


    @Autowired
    public RestControllerUserLight(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @RequestMapping(method = RequestMethod.GET, value = "/common/role")
        Vector<String> getRole(@RequestParam String userid) {
        if (!isAuthorized(userid)) {
            throw new HttpUnauthorizedException();
        }
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Vector<String> autorityString = new Vector<>();
        Vector<String> returned = new Vector<>();
        int i = 0;

        for(SimpleGrantedAuthority authority : authorities){
            autorityString.add(authority.getAuthority());
        }
        if(autorityString.contains("ROLE_USER")){
            returned.add("ROLE_USER");
        }
        else if(autorityString.contains("ROLE_ADMIN")){
            returned.add("ROLE_ADMIN");
        }
        return returned;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/user/userbyid")
    User getUser(@RequestParam String userid){

        return userRepository.findById(userid);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/admin/alluser")
    List<User> getAllUser() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/light")
    Vector<Light> getLightsByUserid(@RequestParam String userid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!isAuthorized(userid)) {
            throw new HttpUnauthorizedException();
        }


        User user = userRepository.findById(userid);
        if (user != null) {
            return userRepository.findById(userid).getLights();
        } else {
            return new Vector<>();
        }


    }


    @RequestMapping(method = RequestMethod.GET, value = "/user/light/consumption")
    double getConsumptionByUserid(@RequestParam String userid) {
        if (!isAuthorized(userid)) {
            throw new HttpUnauthorizedException();
        }
        Vector<Light> lists = userRepository.findById(userid).getLights();
        double consumption = 0;
        for (Light light : lists) {
            if(Objects.equals(light.getStatus(), "on")){
                consumption = consumption + light.getConsumption();
            }

        }
        return consumption;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/setstatus")
    void setStatusByUseridAndLightid(@RequestBody Map map) {

        String userid = String.valueOf(map.get("userid"));
        String lightid = String.valueOf(map.get("lightid"));
        String status = String.valueOf(map.get("status"));
        if (!isAuthorized(userid)) {
            throw new HttpUnauthorizedException();
        }

        User user = userRepository.findById(userid);

        if (user != null) {
            if (Objects.equals(status, "on")) {
                user.switchOnLight(lightid);
            } else if (Objects.equals(status, "off")) {
                user.switchOffLight(lightid);
            }
            userRepository.save(user);
        } else {
            throw new UserIsNotPresent();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/add")
    void addUser(@RequestParam String userid, @RequestParam String name) {

        if (userRepository.findById(userid) != null) {
            throw new UserIsAlreadyPresent();
        }
        User user = new User(userid,name);
        userRepository.save(user);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/addlight")
    void addLightWithUserid(@RequestBody Map map) {

        String userid = String.valueOf(map.get("userid"));
        String lightid = String.valueOf(map.get("lightid"));
        String lighttype = String.valueOf(map.get("lighttype"));
        Double consumption = Double.valueOf(String.valueOf(map.get("consumption")));
        String location = String.valueOf(map.get("location"));
        String state = String.valueOf(map.get("state"));

        if (!isAuthorized(userid)) {
            throw new HttpUnauthorizedException();
        }
        LightClassic l = new LightClassic(lightid, lighttype, consumption, location, "off");
        User user = userRepository.findById(userid);
        if (user == null) {
            throw new UserIsNotPresent();
        }
        if (user.getLight(lightid) != null) {
            throw new IdLightNotUnique();
        }

        user.addLight(l);

        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/deletelight")
    void deleteLightById(@RequestParam("userid") String userId, @RequestParam("lightid")
            String lightid) {


        if (!isAuthorized(userId)) {
            throw new HttpUnauthorizedException();
        }
        User user = userRepository.findById(userId);
        user.deleteLightById(lightid);
        userRepository.save(user);
    }

    boolean isAuthorized(String userId) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean isAuthorized = false;

        System.out.println(authorities);
        for(SimpleGrantedAuthority authority : authorities){
            if(Objects.equals(authority.getAuthority(), "ROLE_" + userId)){
                isAuthorized = true;
            }
        }


        return isAuthorized;

    }


}
