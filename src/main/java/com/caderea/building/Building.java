package com.caderea.building;

/**
 * Created by Cade on 3/27/2016.
 */
public class Building {
    private long id;
    private String name;
    private String address;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name + ": " + address;
    }
}
