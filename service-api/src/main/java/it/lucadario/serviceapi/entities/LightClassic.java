package it.lucadario.serviceapi.entities;

import it.lucadario.lightscomponents.Light;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lucadario on 10/01/18.
 */

public class LightClassic implements Light{



    private String id;
    private String type = "classic";
    private double consumption;
    private String location = "N/A";
    private String status;


    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LightClassic(String id, String type, double consumption, String location, String s) {
        this.id = id;
        this.type = type;
        this.consumption = consumption;
        this.location = location;
        this.status = s;
    }

    public LightClassic() {
    }


    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public boolean switchOn() {
        this.status = "on";
        return true;
    }


    @Override
    public boolean switchOff() {
        this.status = "off";
        return true;
    }
}
