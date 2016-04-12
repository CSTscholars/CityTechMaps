package org.citytechmaps.data.database;

/**
 * Class that only contains constant variables
 *
 * Does not require instantiation for use.
 */
public class S {

    // Building
    static final String TABLE_BUILDING = "building";
    static final String COLUMN_BUILDING_ID = "_id";
    static final String COLUMN_BUILDING_NAME = "name";
    static final String COLUMN_BUILDING_SHORTHAND = "shorthand";
    static final String COLUMN_BUILDING_COLOR = "color";
    static final String COLUMN_BUILDING_ADDRESS = "address";
    static final String COLUMN_BUILDING_LATITUDE = "latitude";
    static final String COLUMN_BUILDING_LONGITUDE = "longitude";
    static final String COLUMN_BUILDING_DESCRIPTION = "description";

    // Building - Department
    static final String TABLE_BUILDING_DEPARTMENT = "building_department";
    static final String COLUMN_BUILDING_DEPARTMENT_ID = "_id";
    static final String COLUMN_BUILDING_DEPARTMENT_BUILDING = "building";
    static final String COLUMN_BUILDING_DEPARTMENT_DEPARTMENT = "department";

    // Building - Floor
    static final String TABLE_BUILDING_FLOOR = "building_floor";
    static final String COLUMN_BUILDING_FLOOR_ID = "_id";
    static final String COLUMN_BUILDING_FLOOR_BUILDING = "building";
    static final String COLUMN_BUILDING_FLOOR_FLOOR = "floor";

    // Department
    static final String TABLE_DEPARTMENT = "department";
    static final String COLUMN_DEPARTMENT_ID = "_id";
    static final String COLUMN_DEPARTMENT_NAME = "name";

    // Floor
    static final String TABLE_FLOOR = "floor";
    static final String COLUMN_FLOOR_ID = "_id";
    static final String COLUMN_FLOOR_NUMBER = "number";
    static final String COLUMN_FLOOR_DESCRIPTION = "description";
    static final String COLUMN_FLOOR_FEMALE_RESTROOM = "female_restroom";
    static final String COLUMN_FLOOR_MALE_RESTROOM = "male_restroom";
    static final String COLUMN_FLOOR_VENDING = "vending";

    // Floor - Department
    static final String TABLE_FLOOR_DEPARTMENT = "floor_department";
    static final String COLUMN_FLOOR_DEPARTMENT_ID = "_id";
    static final String COLUMN_FLOOR_DEPARTMENT_FLOOR = "floor";
    static final String COLUMN_FLOOR_DEPARTMENT_DEPARTMENT = "department";

    // Floor - Room
    static final String TABLE_FLOOR_ROOM = "floor_room";
    static final String COLUMN_FLOOR_ROOM_ID = "_id";
    static final String COLUMN_FLOOR_ROOM_FLOOR = "floor";
    static final String COLUMN_FLOOR_ROOM_ROOM = "room";

    // Room
    static final String TABLE_ROOM = "room";
    static final String COLUMN_ROOM_ID = "_id";
    static final String COLUMN_ROOM_NAME = "name";
    static final String COLUMN_ROOM_X = "x";
    static final String COLUMN_ROOM_Y = "y";
    static final String COLUMN_ROOM_DESCRIPTION = "description";
}
