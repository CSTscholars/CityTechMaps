package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.citytechmaps.data.classes.Room;

/**
 * A convenience class to wrap a cursor that returns rows from the "room"
 * table. The {@link getRoom()} method will give you a Room instance
 * representing the current row.
 */
public class RoomCursor extends CursorWrapper {

    public RoomCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Returns a Room object configured for the current row, or null if the
     * current row is invalid.
     */
    public Room getFloor() {
        if (isBeforeFirst() || isAfterLast())
            return null;

        Room room = new Room();

        long id = getLong(getColumnIndex(S.COLUMN_ROOM_ID));
        String name = getString(getColumnIndex(S.COLUMN_ROOM_NAME));
        int x = getInt(getColumnIndex(S.COLUMN_ROOM_X));
        int y = getInt(getColumnIndex(S.COLUMN_ROOM_Y));
        String description = getString(getColumnIndex(S.COLUMN_ROOM_DESCRIPTION));

        room.setId(id);
        room.setName(name);
        room.setX(x);
        room.setY(y);
        room.setDescription(description);

        return room;
    }
}