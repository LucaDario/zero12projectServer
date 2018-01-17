package it.lucadario.serviceapi.entities;

import it.lucadario.lightscomponents.Light;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Vector;

/**
 * Created by lucadario on 10/01/18.
 */
@Document
public class User {

    @Id
    private String id;
    private String name;
    private Vector<Light> lights = new Vector<>();


    public String getName() {
        return name;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    public Vector<Light> getLights() {
        return lights;
    }

    public void addLight(LightClassic light){
        this.lights.add(light);
    }
    public void switchOnLight(String lightId){
        for(Light light : this.lights){
            if(Objects.equals(light.getId(), lightId)){
                ((LightClassic)light).switchOn();

            }
        }
    }
    public void switchOffLight(String lightId){
        Light l = null;
        for(Light light : this.lights){
            if(Objects.equals(light.getId(), lightId)){
                light.switchOff();

            }
        }
    }

    public Light getLight(String lightId) {
        Light l = null;
        for (Light light : this.lights) {
            if (Objects.equals(light.getId(), lightId)) {
                l = light;

            }
        }
        return l;
    }

    public boolean deleteLightById(String lightId){
        boolean deleted = false;
        Light l = null;
        for (Light light : this.lights) {
            if (Objects.equals(light.getId(), lightId)) {
                l = light;
                deleted = true;

            }
        }
        if(deleted){
            lights.remove(l);
        }
        return deleted;
    }





}
