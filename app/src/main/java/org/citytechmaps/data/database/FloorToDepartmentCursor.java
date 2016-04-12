package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Department;
import org.citytechmaps.data.classes.Floor;
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

        floorToDepartment.setId(id);

        // Get the Floor
        Floor floor = new Floor();

        long floorId = getLong(getColumnIndex(S.COLUMN_FLOOR_DEPARTMENT_FLOOR));
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

        // Get the Department
        Department department = new Department();

        long departmentId = getLong(getColumnIndex(S.COLUMN_FLOOR_DEPARTMENT_DEPARTMENT));
        String departmentName = getString(getColumnIndex(S.COLUMN_DEPARTMENT_NAME));

        department.setId(departmentId);
        department.setName(departmentName);

        floorToDepartment.setId(id);
        floorToDepartment.setFloor(floor);
        floorToDepartment.setDepartment(department);

        return floorToDepartment;
    }
}