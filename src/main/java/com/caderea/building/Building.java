package com.caderea.building;

/**
 * Created by Cade on 3/27/2016.
 */
public class Building {
    private long id;
    private String name;
    private String address;

    private int numFloors;  //ids of floors in this building
    //private int[] rooms; //ids of rooms in this building

    public Building(){}

    public Building(String name, String address, int numFloors){
        this.id = 0;
        this.name = name;
        this.address = address;
        this.numFloors = numFloors;
    }

    public Building(long id, String name, String address, int numFloors){
        this.id = id;
        this.name = name;
        this.address = address;
        this.numFloors = numFloors;
    }

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

    /*public void setRooms(int[] rooms){
        this.rooms = rooms;
    }

    public int[] getRooms(){
        return this.rooms;
    }*/

    public void setNumFloors(int numFloors) {
        this.numFloors = numFloors;
    }

    public int getNumFloors() {
        return numFloors;
    }

    @Override
    public String toString() {
        return getName() + ", " + getAddress() + ", " + getNumFloors() + " floors, ";
    }
}
