package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Building;
import org.citytechmaps.data.classes.BuildingToFloor;
import org.citytechmaps.data.classes.Floor;

/**
 * A convenience class to wrap a cursor that returns rows from the "building_floor"
 * table. The {@link getBuildingToFloor()} method will give you a BuildingToFloor instance
 * representing the current row.
 */
public class BuildingToFloorCursor extends CursorWrapper {

    public BuildingToFloorCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a Building object configured for the current row, or null if the
     * current row is invalid.
     */
    public BuildingToFloor getBuildingToFloor() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        BuildingToFloor buildingToFloor = new BuildingToFloor();

        long id = getLong(getColumnIndex(S.COLUMN_BUILDING_FLOOR_ID));

        buildingToFloor.setId(id);

        // Get the Building
        Building building = new Building();

        long buildingId =  getLong(getColumnIndex(S.COLUMN_BUILDING_DEPARTMENT_BUILDING));
        String buildingName = getString(getColumnIndex(S.COLUMN_BUILDING_NAME));
        String buildingShorthand = getString(getColumnIndex(S.COLUMN_BUILDING_SHORTHAND));
        String buildingColor = getString(getColumnIndex(S.COLUMN_BUILDING_COLOR));
        String buildingAddress = getString(getColumnIndex(S.COLUMN_BUILDING_ADDRESS));
        double latitude = getDouble(getColumnIndex(S.COLUMN_BUILDING_LATITUDE));
        double longitude = getDouble(getColumnIndex(S.COLUMN_BUILDING_LONGITUDE));
        String buildingDesc = getString(getColumnIndex(S.COLUMN_BUILDING_DESCRIPTION));

        building.setId(buildingId);
        building.setName(buildingName);
        building.setShorthand(buildingShorthand);
        building.setColor(buildingColor);
        building.setAddress(buildingAddress);
        building.setLatitude(latitude);
        building.setLongitude(longitude);
        building.setDescription(buildingDesc);

        // Get the Floor
        Floor floor = new Floor();

        long floorId = getLong(getColumnIndex(S.COLUMN_FLOOR_ID));
        String floorNumber = getString(getColumnIndex(S.COLUMN_FLOOR_NUMBER));
        String floorDesc = getString(getColumnIndex(S.COLUMN_FLOOR_DESCRIPTION));
        int frestroom = getInt(getColumnIndex(S.COLUMN_FLOOR_FEMALE_RESTROOM));
        int mrestroom = getInt(getColumnIndex(S.COLUMN_FLOOR_MALE_RESTROOM));
        int vending = getInt(getColumnIndex(S.COLUMN_FLOOR_VENDING));

        floor.setId(floorId);
        floor.setNumber(floorNumber);
        floor.setDescription(floorDesc);
        floor.setFemaleRestroom(frestroom);
        floor.setMaleRestroom(mrestroom);
        floor.setVending(vending);

        buildingToFloor.setBuilding(building);
        buildingToFloor.setFloor(floor);

        return buildingToFloor;
    }
}

