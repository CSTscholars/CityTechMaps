package org.citytechmaps.loader;

import android.content.Context;
import android.database.Cursor;

import org.citytechmaps.data.database.DataManager;

public class FloorListCursorLoader extends SQLiteCursorLoader{

    private long buildingId;

    public FloorListCursorLoader(Context context, long buildingId) {
        super(context);
        this.buildingId = buildingId;
    }

    @Override
    protected Cursor loadCursor() {
        // Query the list of all floors in specified building
        return DataManager.getInstance(getContext()).queryBuildingToFloors(buildingId);
    }
}
