package org.citytechmaps.data.classes;

public class FloorToDepartment {

    private long id;
    private long floor;
    private long department;

    public FloorToDepartment() {
        this.id = -1;
        this.floor = -1;
        this.department = -1;
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

    public long getDepartment() {
        return department;
    }

    public void setDepartment(long department) {
        this.department = department;
    }
}
