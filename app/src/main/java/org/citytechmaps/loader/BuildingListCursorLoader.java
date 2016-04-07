package org.citytechmaps.loader;

import android.content.Context;
import android.database.Cursor;

import org.citytechmaps.data.database.DataManager;

public class BuildingListCursorLoader extends SQLiteCursorLoader{

    public BuildingListCursorLoader(Context context) {
        super(context);
    }

    @Override
    protected Cursor loadCursor() {
        // Query the list of all buildings
        return DataManager.getInstance(getContext()).queryBuildings();
    }
}
