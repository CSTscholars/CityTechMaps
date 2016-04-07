package org.citytechmaps.data.classes;

public class FloorToRoom {

    private long id;
    private long floor;
    private long room;

    public FloorToRoom() {
        this.id = -1;
        this.floor = -1;
        this.room = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFloor() {
        return floor;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
    }
}
