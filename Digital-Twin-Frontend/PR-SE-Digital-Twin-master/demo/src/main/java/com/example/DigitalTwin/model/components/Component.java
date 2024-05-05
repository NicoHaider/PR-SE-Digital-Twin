package com.example.DigitalTwin.model.components;

import java.util.Date;

public abstract class Component {

    private int id;
    private String name;
    private Boolean status;
    private Date time;

    public Component(String name){
        this.name = name;
        this.status = false;
    }

    public Component(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public int getId() {
        return id;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return this.name + ": " + this.status;
    }
}

