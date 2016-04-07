package org.citytechmaps.data.classes;

public class Department {

    private long id;
    private String name;

    public Department() {
        this.id = -1;
        this.name = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
