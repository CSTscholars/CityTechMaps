package org.citytechmaps.data.classes;

public class Floor {

    private long id;
    private String number;
    private String description;
    private int female_restroom;
    private int male_restroom;
    private int vending;

    public Floor() {
        this.id = -1;
        this.number = "";
        this.description = "";
        this.female_restroom = 0;
        this.male_restroom = 0;
        this.vending = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFemaleRestroom() {
        return female_restroom;
    }

    public void setFemaleRestroom(int female_restroom) {
        this.female_restroom = female_restroom;
    }

    public int getMaleRestroom() {
        return male_restroom;
    }

    public void setMaleRestroom(int male_restroom) {
        this.male_restroom = male_restroom;
    }

    public int getVending() {
        return vending;
    }

    public void setVending(int vending) {
        this.vending = vending;
    }
}
