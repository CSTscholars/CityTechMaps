package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Floor;

/**
 * A convenience class to wrap a cursor that returns rows from the "floor"
 * table. The {@link getFloor()} method will give you a Floor instance
 * representing the current row.
 */
public class FloorCursor extends CursorWrapper {

    public FloorCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a Floor object configured for the current row, or null if the
     * current row is invalid.
     */
    public Floor getFloor() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        Floor floor = new Floor();

        long id = getLong(getColumnIndex(S.COLUMN_FLOOR_ID));
        String number = getString(getColumnIndex(S.COLUMN_FLOOR_NUMBER));
        String description = getString(getColumnIndex(S.COLUMN_FLOOR_DESCRIPTION));
        int femaleRestroom = getInt(getColumnIndex(S.COLUMN_FLOOR_FEMALE_RESTROOM));
        int maleRestroom = getInt(getColumnIndex(S.COLUMN_FLOOR_MALE_RESTROOM));
        int vending = getInt(getColumnIndex(S.COLUMN_FLOOR_VENDING));

        floor.setId(id);
        floor.setNumber(number);
        floor.setDescription(description);
        floor.setFemaleRestroom(femaleRestroom);
        floor.setMaleRestroom(maleRestroom);
        floor.setVending(vending);

        return floor;
    }
}