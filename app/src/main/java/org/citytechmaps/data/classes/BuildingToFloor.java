package org.citytechmaps.data.classes;

public class BuildingToFloor {

    private long id;
    private long building;
    private long floor;

    public BuildingToFloor() {
        this.id = -1;
        this.building = -1;
        this.floor = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuilding() {
        return building;
    }

    public void setBuilding(long building) {
        this.building = building;
    }

    public long getFloor() {
        return floor;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }
}
