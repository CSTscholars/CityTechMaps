package org.citytechmaps.loader;

import android.content.Context;

import org.citytechmaps.data.classes.Building;

public class BuildingLoader extends DataLoader<Building> {
    private long id;

    public BuildingLoader(Context context, long id) {
        super(context);
        this.id = id;
    }

    @Override
    public Building loadInBackground() {
        // Query the specific building
        return null;
    }
}
