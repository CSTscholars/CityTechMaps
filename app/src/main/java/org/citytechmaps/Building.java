package org.citytechmaps;

/**
 * Created by Raff on 3/11/2016.
 */
public class Building {
    private int id;
    private String name;
    private String address;
    private int x, y;
    private String description;

    public Building() {
    }

    public Building(int id, String name, String address, int x, int y, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
