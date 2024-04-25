package com.example.DigitalTwin.model.components;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fan extends Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int fanID;

    public Fan(String name) {
        super(name);
    }

    public Fan(String name, int acID) {
        super(name);
        this.fanID = fanID;
    }

    public void setFanID(int acID) {
        this.fanID = fanID;
    }

    public int getFanID() {
        return fanID;
    }




}

