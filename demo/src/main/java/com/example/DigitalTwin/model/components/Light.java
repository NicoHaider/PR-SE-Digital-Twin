package com.example.DigitalTwin.model.components;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Light extends Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int lightID;

    public Light(String name) {
        super(name);
    }

    public Light(String name, int lightID) {
        super(name);
        this.lightID = lightID;
    }

    public void setLightID(int lightID) {
        this.lightID = lightID;
    }

    public int getLightID() {
        return lightID;
    }

    @Override
    public String toString(){
        if(getStatus()){
            return this.getName() + " " + getLightID() + ": On";
        }else{
            return this.getName() + " " + getLightID() + ": Off";
        }
    }
}

