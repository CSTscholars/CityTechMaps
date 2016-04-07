package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.FloorToRoom;

/**
 * A convenience class to wrap a cursor that returns rows from the "floor_room"
 * table. The {@link getFloorToRoom()} method will give you a FloorToRoom instance
 * representing the current row.
 */
public class FloorToRoomCursor extends CursorWrapper {

    public FloorToRoomCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a FloorToRoom object configured for the current row, or null if the
     * current row is invalid.
     */
    public FloorToRoom getBuildingToDepartment() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        FloorToRoom floorToRoom = new FloorToRoom();

        long id = getLong(getColumnIndex(S.COLUMN_FLOOR_ROOM_ID));
        long floor = getLong(getColumnIndex(S.COLUMN_FLOOR_ROOM_FLOOR));
        long room = getLong(getColumnIndex(S.COLUMN_FLOOR_ROOM_ROOM));

        floorToRoom.setId(id);
        floorToRoom.setFloor(floor);
        floorToRoom.setRoom(room);

        return floorToRoom;
    }
}