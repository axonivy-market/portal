package ch.ivyteam.ivy.project.portal.examples.showcase;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class RadioView implements Serializable {

    private static final long serialVersionUID = 7183563000838516291L;
    private String console;    
    private String city; 
    private String city2; 
    private ArrayList<String> cities;  
    private String color;
     

    public void init() {
        cities = new ArrayList<String>();
        cities.add("Miami");
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Berlin");
        cities.add("Barcelona");
        cities.add("Rome");
        cities.add("Brasilia");
        cities.add("Amsterdam");
    }
 
    public String getConsole() {
        return console;
    }
 
    public void setConsole(String console) {
        this.console = console;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getCity2() {
        return city2;
    }
 
    public void setCity2(String city2) {
        this.city2 = city2;
    }
     
    public String getColor() {
        return color;
    }
 
    public void setColor(String color) {
        this.color = color;
    }
 
    public ArrayList<String> getCities() {
        return cities;
    }
}