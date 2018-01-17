package it.lucadario.lightscomponents;

/**
 * Created by lucadario on 08/01/18.
 */
public interface LightController {
    boolean switchOn(String location);
    boolean switchOff(String location);
    double getConsumption();


}
