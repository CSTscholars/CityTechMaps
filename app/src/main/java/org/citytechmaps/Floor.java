package org.citytechmaps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Raff on 3/11/2016.
 */
public class Floor implements Parcelable {

    private int id;
    private int buildingId;
    private String[] departments;
    private Room[] rooms;

    public Floor() {
        id = 0;
        departments = null;
        rooms = null;
    }

    public Floor(int id, String[] departments, Room[] rooms, int buildingId) {
        this.id = id;
        this.departments = departments;
        this.rooms = rooms;
        this.buildingId = buildingId;
    }

    protected Floor(Parcel in) {
        id = in.readInt();
        departments = in.createStringArray();
        rooms = in.createTypedArray(Room.CREATOR);
        buildingId = in.readInt();
    }

    public static final Creator<Floor> CREATOR = new Creator<Floor>() {
        @Override
        public Floor createFromParcel(Parcel in) {
            return new Floor(in);
        }

        @Override
        public Floor[] newArray(int size) {
            return new Floor[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringArray(departments);
        dest.writeTypedArray(rooms, flags);
        dest.writeInt(buildingId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getDepartments() {
        return departments;
    }

    public void setDepartments(String[] departments) {
        this.departments = departments;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
