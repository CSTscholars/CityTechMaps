package org.citytechmaps.data.classes;

public class FloorToDepartment {

    private long id;
    private Floor floor;
    private Department department;

    public FloorToDepartment() {
        this.id = -1;
        this.floor = null;
        this.department = null;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
