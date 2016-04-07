package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.FloorToDepartment;

/**
 * A convenience class to wrap a cursor that returns rows from the "floor_department"
 * table. The {@link getFloorToDepartment()} method will give you a FloorToDepartment instance
 * representing the current row.
 */
public class FloorToDepartmentCursor extends CursorWrapper {

    public FloorToDepartmentCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a FloorToDepartment object configured for the current row, or null if the
     * current row is invalid.
     */
    public FloorToDepartment getBuildingToDepartment() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        FloorToDepartment floorToDepartment = new FloorToDepartment();

        long id = getLong(getColumnIndex(S.COLUMN_FLOOR_DEPARTMENT_ID));
        long floor = getLong(getColumnIndex(S.COLUMN_FLOOR_DEPARTMENT_FLOOR));
        long department = getLong(getColumnIndex(S.COLUMN_FLOOR_DEPARTMENT_DEPARTMENT));

        floorToDepartment.setId(id);
        floorToDepartment.setFloor(floor);
        floorToDepartment.setDepartment(department);

        return floorToDepartment;
    }
}