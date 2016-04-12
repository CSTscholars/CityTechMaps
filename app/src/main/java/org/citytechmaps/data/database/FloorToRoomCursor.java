package org.citytechmaps.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.hardware.camera2.params.StreamConfigurationMap;

import org.citytechmaps.data.classes.Floor;
import org.citytechmaps.data.classes.FloorToRoom;
import org.citytechmaps.data.classes.Room;

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

        floorToRoom.setId(id);

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

        //Get the Room
        Room room = new Room();

        long roomID = getLong(getColumnIndex(S.COLUMN_FLOOR_ROOM_ROOM));
        String roomName = getString(getColumnIndex(S.COLUMN_ROOM_NAME));
        int x = getInt(getColumnIndex(S.COLUMN_ROOM_X));
        int y = getInt(getColumnIndex(S.COLUMN_ROOM_Y));
        String roomDesc = getString(getColumnIndex(S.COLUMN_ROOM_DESCRIPTION));

        room.setId(roomID);
        room.setName(roomName);
        room.setX(x);
        room.setY(y);
        room.setDescription(roomDesc);

        floorToRoom.setFloor(floor);
        floorToRoom.setRoom(room);

        return floorToRoom;
    }
}