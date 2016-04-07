package org.citytechmaps.data.classes;

public class BuildingToDepartment {

    private long id;
    private long building;
    private long department;

    public BuildingToDepartment() {
        this.id = -1;
        this.building = -1;
        this.department = -1;
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

    public long getDepartment() {
        return department;
    }

    public void setDepartment(long department) {
        this.department = department;
    }
}
