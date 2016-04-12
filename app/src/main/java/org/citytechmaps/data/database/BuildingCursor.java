package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Building;

/**
 * A convenience class to wrap a cursor that returns rows from the "building"
 * table. The {@link getBuilding()} method will give you a Building instance
 * representing the current row.
 */
public class BuildingCursor extends CursorWrapper{

    public BuildingCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a Building object configured for the current row, or null if the
     * current row is invalid.
     */
    public Building getBuilding() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        Building building = new Building();

        long id = getLong(getColumnIndex(S.COLUMN_BUILDING_ID));
        String name = getString(getColumnIndex(S.COLUMN_BUILDING_NAME));
        String shorthand = getString(getColumnIndex(S.COLUMN_BUILDING_SHORTHAND));
        String color = getString(getColumnIndex(S.COLUMN_BUILDING_COLOR));
        String address = getString(getColumnIndex(S.COLUMN_BUILDING_ADDRESS));
        double latitude = getDouble(getColumnIndex(S.COLUMN_BUILDING_LATITUDE));
        double longitude = getDouble(getColumnIndex(S.COLUMN_BUILDING_LONGITUDE));
        String description = getString(getColumnIndex(S.COLUMN_BUILDING_DESCRIPTION));

        building.setId(id);
        building.setName(name);
        building.setShorthand(shorthand);
        building.setColor(color);
        building.setAddress(address);
        building.setLatitude(latitude);
        building.setLongitude(longitude);
        building.setDescription(description);

        return building;
    }
}
