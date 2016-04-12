package org.citytechmaps.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.citytechmaps.data.classes.Floor;

import java.sql.SQLException;
import java.util.HashMap;

/*
 * Singleton Class
 */

public class CityTechDatabaseHelper extends SQLiteAssetHelper {
    private static final String TAG = "CityTechDatabaseHelper";

    private static CityTechDatabaseHelper instance = null;

    private static final String DATABASE_NAME = "citytechmaps.db";
    private static final int DATABASE_VERSION = 12;

    private final Context context;
    private SQLiteDatabase database;

    /**
     * Returns Singleton instance of the helper object
     *
     * @param c Application context
     * @return Singleton instance of helper object
     */
    public static CityTechDatabaseHelper getInstance(Context c) {
        /*
         * Securing up to a single instance of
         * the helper object.
         * This is NOT thread safe.
         */
        if (instance == null) {
            instance = new CityTechDatabaseHelper(c.getApplicationContext());
        }
        return instance;
    }

    private CityTechDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        setForcedUpgrade();
    }

    /**
     * Check if the specified table exists within the given database.
     *
     * @param tableName Table's name
     * @param db        Database within which to check for the specified table
     * @return Whether the specified table exists within the given database
     */
    public boolean isTableExists(String tableName, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name from  sqlite_master WHERE tbl_name = '" + tableName + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    private String replaceNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * Set database instance
     *
     * @throws SQLException
     */
    public void openDatabase() throws SQLException {
        database = getWritableDatabase();
    }

    @Override
    public synchronized void close() {
        if (database != null) database.close();
        super.close();
    }

    /*
     * Helper method: used for queries that has no JOINs
     */
    private Cursor wrapHelper(QueryHelper qh) {
        //Log.d(TAG, "ROWS QUERIED: " + getWritableDatabase().rawQuery("SELECT DISTINCT * FROM building ORDER BY _id", null).getCount());
        return getWritableDatabase().query(qh.Distinct, qh.Table, qh.Columns, qh.Selection, qh.SelectionArgs, qh.GroupBy, qh.Having, qh.OrderBy, qh.Limit);
    }

    /*
     * Helper method: used for queries that has no JOINs
     */
    private Cursor wrapHelper(SQLiteDatabase db, QueryHelper qh) {
        return db.query(qh.Distinct, qh.Table, qh.Columns, qh.Selection, qh.SelectionArgs, qh.GroupBy, qh.Having, qh.OrderBy, qh.Limit);
    }

    /*
     * Helper method: used for queries that has JOINs
     */
    private Cursor wrapJoinHelper(SQLiteQueryBuilder qb, QueryHelper qh) {
//		Log.d(TAG, "qb: " + qb.buildQuery(_Columns, _Selection, _SelectionArgs, _GroupBy, _Having, _OrderBy, _Limit));
        return qb.query(getWritableDatabase(), qh.Columns, qh.Selection, qh.SelectionArgs, qh.GroupBy, qh.Having, qh.OrderBy, qh.Limit);
    }

    /*
     * Helper method: used for queries that has JOINs
     */
    private Cursor wrapJoinHelper(SQLiteDatabase db, SQLiteQueryBuilder qb, QueryHelper qh) {
//		Log.d(TAG, "qb: " + qb.buildQuery(_Columns, _Selection, _SelectionArgs, _GroupBy, _Having, _OrderBy, _Limit));
        return qb.query(db, qh.Columns, qh.Selection, qh.SelectionArgs, qh.GroupBy, qh.Having, qh.OrderBy, qh.Limit);
    }

    /*
     * Insert data to a table
     */
    public long insertRecord(String table, ContentValues values) {
        long l = getWritableDatabase().insert(table, null, values);
        return l;
    }

    /*
     * Insert data to a table
     */
    public long insertRecord(SQLiteDatabase db, String table, ContentValues values) {
        long l = db.insert(table, null, values);
        return l;
    }

    /*
     * Update data in a table
     */
    public int updateRecord(String table, String strFilter, ContentValues values) {
        int i = getWritableDatabase().update(table, values, strFilter, null);
        return i;
    }

    /*
     * Delete data in a table
     */
    public boolean deleteRecord(String table, String where, String[] args) {
        boolean b = getWritableDatabase().delete(table, where, args) > 0;
        return b;
    }

    /****************************** BUILDING QUERIES ******************************/

    /**
     * Get all buildings
     *
     * SELECT DISTINCT * FROM building ORDER BY _id
     */
    public BuildingCursor queryBuildings() {
        QueryHelper qh = new QueryHelper();
        qh.Distinct = true;
        qh.Table = S.TABLE_BUILDING;
        qh.Columns = null;
        qh.Selection = null;
        qh.SelectionArgs = null;
        qh.GroupBy = null;
        qh.Having = null;
        qh.OrderBy = S.COLUMN_BUILDING_ID;
        qh.Limit = null;

        return new BuildingCursor(wrapHelper(qh));
    }

    /**
     * Get building with specified id
     *
     * SELECT DISTINCT * FROM building WHERE _id = id LIMIT 1
     */
    public BuildingCursor queryBuilding(long id) {
        QueryHelper qh = new QueryHelper();
        qh.Distinct = true;
        qh.Table = S.TABLE_BUILDING;
        qh.Columns = null;
        qh.Selection = S.COLUMN_BUILDING_ID + " = ? ";
        qh.SelectionArgs = new String[]{String.valueOf(id)};
        qh.GroupBy = null;
        qh.Having = null;
        qh.OrderBy = null;
        qh.Limit = "1";

        return new BuildingCursor(wrapHelper(qh));
    }

    /**
     * Get all the ids of all floors in specified building
     *
     * SELECT DISTINCT * FROM building_floor WHERE building = buildingId
     */
    public BuildingToFloorCursor queryBuildingToFloors(long buildingId) {
        QueryHelper qh = new QueryHelper();
        qh.Distinct = true;
        qh.Table = S.TABLE_BUILDING_FLOOR;
        qh.Columns = null;
        qh.Selection = S.COLUMN_BUILDING_FLOOR_BUILDING + " = ?";
        qh.SelectionArgs = new String[]{String.valueOf(buildingId)};
        qh.GroupBy = null;
        qh.Having = null;
        qh.OrderBy = null;
        qh.Limit = null;

        return new BuildingToFloorCursor(wrapJoinHelper(builderBuildingToFloors(qh.Distinct), qh));
    }

    /**
     * Gets floor with specified id
     *
     * SELECT DISTINCT * FROM floor WHERE _id = id
     */
    public FloorCursor queryFloor(long id) {
        QueryHelper qh = new QueryHelper();
        qh.Distinct = true;
        qh.Table = S.TABLE_BUILDING_FLOOR;
        qh.Columns = null;
        qh.Selection = S.COLUMN_FLOOR_ID + " = ? ";
        qh.SelectionArgs = new String[]{String.valueOf(id)};
        qh.GroupBy = null;
        qh.Having = null;
        qh.OrderBy = null;
        qh.Limit = "1";

        return new FloorCursor(wrapHelper(qh));
    }

    /*
     * Helper method to query for BuildingToFloor
     */
    private SQLiteQueryBuilder builderBuildingToFloors(boolean distinct) {
        /*
         * SELECT btf._id AS _id, btf.building, btf.floor,
         * b.name, b.shorthand, b.color, b.address, b.latitude, b.longitude, b.description,
         * f.number, f.description, f.female_restroom, f.male_restroom, f.vending
         * FROM building_floor AS btf
         * LEFT OUTER JOIN building AS b ON btf.building = b._id
         * LEFT OUTER JOIN floor AS f ON btf.floor = f._id;
         */

        String btf = "btf";
        String b = "b";
        String f = "f";

        HashMap<String, String> projectionMap = new HashMap<>();

        projectionMap.put("_id", btf + "." + S.COLUMN_BUILDING_FLOOR_ID + " AS " + " _id");

        projectionMap.put(S.COLUMN_BUILDING_FLOOR_BUILDING, btf + "." + S.COLUMN_BUILDING_FLOOR_BUILDING);
        projectionMap.put(S.COLUMN_BUILDING_FLOOR_FLOOR, btf + "." + S.COLUMN_BUILDING_FLOOR_FLOOR);
        projectionMap.put(S.COLUMN_BUILDING_FLOOR_BUILDING, btf + "." + S.COLUMN_BUILDING_FLOOR_BUILDING);

        projectionMap.put(S.COLUMN_BUILDING_NAME, b + "." + S.COLUMN_BUILDING_NAME);
        projectionMap.put(S.COLUMN_BUILDING_SHORTHAND, b + "." + S.COLUMN_BUILDING_SHORTHAND);
        projectionMap.put(S.COLUMN_BUILDING_COLOR, b + "." + S.COLUMN_BUILDING_COLOR);
        projectionMap.put(S.COLUMN_BUILDING_ADDRESS, b + "." + S.COLUMN_BUILDING_ADDRESS);
        projectionMap.put(S.COLUMN_BUILDING_LATITUDE, b + "." + S.COLUMN_BUILDING_LATITUDE);
        projectionMap.put(S.COLUMN_BUILDING_LONGITUDE, b + "." + S.COLUMN_BUILDING_LONGITUDE);
        projectionMap.put(S.COLUMN_BUILDING_DESCRIPTION, b + "." + S.COLUMN_BUILDING_DESCRIPTION);

        projectionMap.put(S.COLUMN_FLOOR_NUMBER, f + "." + S.COLUMN_FLOOR_NUMBER);
        projectionMap.put(S.COLUMN_FLOOR_DESCRIPTION, f + "." + S.COLUMN_FLOOR_DESCRIPTION);
        projectionMap.put(S.COLUMN_FLOOR_FEMALE_RESTROOM, f + "." + S.COLUMN_FLOOR_FEMALE_RESTROOM);
        projectionMap.put(S.COLUMN_FLOOR_MALE_RESTROOM, f + "." + S.COLUMN_FLOOR_MALE_RESTROOM);
        projectionMap.put(S.COLUMN_FLOOR_VENDING, f + "." + S.COLUMN_FLOOR_VENDING);

        // Set up QueryBuilder
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(S.TABLE_BUILDING_FLOOR +  " AS " + btf
                    + " LEFT OUTER JOIN " + S.TABLE_BUILDING + " AS " + b
                    + " ON " + btf + "." + S.COLUMN_BUILDING_FLOOR_BUILDING + " = " + b + "." + S.COLUMN_BUILDING_ID
                    + " LEFT OUTER JOIN " + S.TABLE_FLOOR + " AS " + f
                    + " ON " + btf + "." + S.COLUMN_BUILDING_FLOOR_FLOOR + " = " + f + "." + S.COLUMN_FLOOR_ID);

        qb.setDistinct(distinct);
        qb.setProjectionMap(projectionMap);

        return qb;
    }
}
