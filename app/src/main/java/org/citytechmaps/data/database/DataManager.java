package org.citytechmaps.data.database;

import android.content.Context;

import org.citytechmaps.data.classes.Building;

/*
 * Singleton Class
 */

public class DataManager {
    private static final String TAG = "DataManager";

    private static DataManager instance = null;

    private Context context;
    private CityTechDatabaseHelper helper;

    /**
     * Returns Singleton instance of the manager object
     *
     * @param c Application context
     * @return Singleton instance of manager object
     */

    public static DataManager getInstance(Context c) {
        /*
         * Securing up to a single instance of
         * the helper object.
         * This is NOT thread safe.
         */
        if (instance == null) {
            instance = new DataManager(c.getApplicationContext());
        }
        return instance;
    }

    private DataManager(Context context) {
        this.context = context;
        helper = CityTechDatabaseHelper.getInstance(this.context);
    }

    /****************************** BUILDING QUERIES ******************************/

    /**
     * Get all buildings
     *
     * @return Cursor containing all rows in the "building" table.
     */
    public BuildingCursor queryBuildings() {
        return helper.queryBuildings();
    }

    /**
     * Returns specified Building ID as Building object.
     *
     * @param id ID of building in question
     * @return Building object representing the ID provided
     */
    public Building queryBuilding(long id) {
        Building building =  null;
        BuildingCursor cursor = helper.queryBuilding(id);
        cursor.moveToFirst();

        if (!cursor.isAfterLast())
            building = cursor.getBuilding();

        cursor.close();
        return building;
    }

    /**
     * Returns all building-floor pairs associated with specified building id.
     *
     * @param buildingId ID of building in question
     * @return Cursor containing all building-floor object pairs
     */
    public BuildingToFloorCursor queryBuildingToFloors(long buildingId) {
        return helper.queryBuildingToFloors(buildingId);
    }
}
