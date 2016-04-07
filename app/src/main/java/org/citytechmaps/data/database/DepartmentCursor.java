package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Department;

/**
 * A convenience class to wrap a cursor that returns rows from the "department"
 * table. The {@link getDepartment())} method will give you a Department instance
 * representing the current row.
 */
public class DepartmentCursor extends CursorWrapper{

    public DepartmentCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a Department object configured for the current row, or null if the
     * current row is invalid.
     */
    public Department getBuilding() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        Department department = new Department();

        long id = getLong(getColumnIndex(S.COLUMN_DEPARTMENT_ID));
        String name = getString(getColumnIndex(S.COLUMN_DEPARTMENT_NAME));

        department.setId(id);
        department.setName(name);

        return department;
    }
}
