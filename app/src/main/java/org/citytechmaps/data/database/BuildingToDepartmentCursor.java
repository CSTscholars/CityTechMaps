package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.BuildingToDepartment;

/**
 * A convenience class to wrap a cursor that returns rows from the "building_department"
 * table. The {@link getBuildingToDepartment()} method will give you a BuildingToDepartment instance
 * representing the current row.
 */
public class BuildingToDepartmentCursor extends CursorWrapper {

    public BuildingToDepartmentCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a Building object configured for the current row, or null if the
     * current row is invalid.
     */
    public BuildingToDepartment getBuildingToDepartment() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        BuildingToDepartment buildingToDepartment = new BuildingToDepartment();

        long id = getLong(getColumnIndex(S.COLUMN_BUILDING_DEPARTMENT_ID));
        long building = getLong(getColumnIndex(S.COLUMN_BUILDING_DEPARTMENT_BUILDING));
        long department = getLong(getColumnIndex(S.COLUMN_BUILDING_DEPARTMENT_DEPARTMENT));

        buildingToDepartment.setId(id);
        buildingToDepartment.setBuilding(building);
        buildingToDepartment.setDepartment(department);

        return buildingToDepartment;
    }
}
