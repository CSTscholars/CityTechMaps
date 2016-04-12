package org.citytechmaps.data.classes;

public class BuildingToDepartment {

    private long id;
    private Building building;
    private Department department;

    public BuildingToDepartment() {
        this.id = -1;
        this.building = null;
        this.department = null;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
