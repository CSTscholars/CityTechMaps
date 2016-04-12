package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Building;
import org.citytechmaps.data.classes.BuildingToDepartment;
import org.citytechmaps.data.classes.Department;

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

        buildingToDepartment.setId(id);

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

        // Get the Department
        Department department = new Department();

        long departmentId = getLong(getColumnIndex(S.COLUMN_DEPARTMENT_ID));
        String departmentName = getString(getColumnIndex(S.COLUMN_DEPARTMENT_NAME));

        department.setId(departmentId);
        department.setName(departmentName);

        buildingToDepartment.setBuilding(building);
        buildingToDepartment.setDepartment(department);

        return buildingToDepartment;
    }
}
