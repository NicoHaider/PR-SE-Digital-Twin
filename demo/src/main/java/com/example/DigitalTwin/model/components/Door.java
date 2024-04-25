package com.example.DigitalTwin.model.components;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Door extends Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doorID;
    public Door(String name) {
        super(name);
    }

    public Door(String name, int doorID) {
        super(name);
        this.doorID = doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public int getDoorID() {
        return doorID;
    }


    @Override
    public String toString(){
        if(getStatus()){
            return this.getName() + " " + getDoorID() + ": Open";
        }else{
            return this.getName() + " " + getDoorID() + ": Closed";
        }
    }

}
