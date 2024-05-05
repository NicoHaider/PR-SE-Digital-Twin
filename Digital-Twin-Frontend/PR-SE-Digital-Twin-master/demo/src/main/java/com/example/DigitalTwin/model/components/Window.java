package com.example.DigitalTwin.model.components;

import jakarta.persistence.*;

@Entity
@Table(name = "win_dows")
public class Window extends Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int windowID;

    public Window(String name) {
        super(name);
    }

    public Window(String name, int windowID) {
        super(name);
        this.windowID = windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }

    public int getWindowID() {
        return windowID;
    }


    @Override
    public String toString(){
        if(getStatus()){
            return this.getName() + " " + getWindowID() + ": Open";
        }else{
            return this.getName() + " " + getWindowID() + ": Closed";
        }
    }
}

