package org.citytechmaps.data.classes;

public class BuildingToFloor {

    private long id;
    private Building building;
    private Floor floor;

    public BuildingToFloor() {
        this.id = -1;
        this.building = null;
        this.floor = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
