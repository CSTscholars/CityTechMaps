package org.citytechmaps.data.classes;

public class Room {
    private long id;
    private String name;
    private int x, y;
    private String description;

    public Room() {
        this.id = -1;
        this.name = "";
        this.x = 0;
        this.y = 0;
        this.description = "";
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}