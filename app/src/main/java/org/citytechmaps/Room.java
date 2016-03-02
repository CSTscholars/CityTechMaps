package org.citytechmaps;

/**
 * Created by Raff on 2/28/2016.
 */
public class Room {
    private int id;
    private String name;
    private int x, y;
    private int floor;
    private String description;

    public Room() {
    }

    public Room(int id) {
        this.id = id;
    }

    public Room(int id, String name, int x, int y, int floor, String description) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
