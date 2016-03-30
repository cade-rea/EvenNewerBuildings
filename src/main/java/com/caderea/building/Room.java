package com.caderea.building;

/**
 * Created by Cade on 3/29/2016.
 */
public class Room {
    private long id;
    private long buildingId; //id of buildingId this room is in
    private long floorId; //id of floorId this room is on

    public Room(){}
    public Room(long id, long buildingId, long floorId){
        this.id = id;
        this.buildingId = buildingId;
        this.floorId = floorId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setFloorId(int floorId){
        this.floorId = floorId;
    }

    public long getFloorId() {
        return this.floorId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public String toString(){
        return "ID:" + getId() + ", Building:" + getBuildingId() + ", Floor:" + getFloorId();
    }
}
