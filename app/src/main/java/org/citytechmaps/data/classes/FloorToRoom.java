package org.citytechmaps.data.classes;

public class FloorToRoom {

    private long id;
    private Floor floor;
    private Room room;

    public FloorToRoom() {
        this.id = -1;
        this.floor = null;
        this.room = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
