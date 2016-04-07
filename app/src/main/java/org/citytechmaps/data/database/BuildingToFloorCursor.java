package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.BuildingToFloor;

/**
 * Created by Raff on 4/5/2016.
 */
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
        long building = getLong(getColumnIndex(S.COLUMN_BUILDING_FLOOR_BUILDING));
        long floor = getLong(getColumnIndex(S.COLUMN_BUILDING_FLOOR_FLOOR));

        buildingToFloor.setId(id);
        buildingToFloor.setBuilding(building);
        buildingToFloor.setFloor(floor);

        return buildingToFloor;
    }
}

