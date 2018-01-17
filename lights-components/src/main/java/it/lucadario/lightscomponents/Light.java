package it.lucadario.lightscomponents;

/**
 * Created by lucadario on 08/01/18.
 */

//rappresenta una lampadina, non fisicamente essa ma il suo rispettivo oggetto quandov viene
    //aggiunta al controller
public interface Light {

    String getId();

    String getType();
    String getLocation();
    String getStatus();
    void setLocation(String location);
    boolean switchOn();
    boolean switchOff();
    double getConsumption();




}
