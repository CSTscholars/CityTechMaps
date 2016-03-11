package org.citytechmaps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Raff on 2/28/2016.
 */
public class Room implements Parcelable{
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

    protected Room(Parcel in) {
        id = in.readInt();
        name = in.readString();
        x = in.readInt();
        y = in.readInt();
        floor = in.readInt();
        description = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(floor);
        dest.writeString(description);
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
