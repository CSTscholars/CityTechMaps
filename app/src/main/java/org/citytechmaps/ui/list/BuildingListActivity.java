package org.citytechmaps.ui.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

import org.citytechmaps.ui.list.adapter.MenuSection;
import org.citytechmaps.ui.general.GenericActivity;

public class BuildingListActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Buildings");

        // Top level activity
        super.setAsTopLevel();
    }

    @Override
    protected MenuSection getSelectedSection() {
        return MenuSection.BUILDINGS;
    }

    @Override
    protected Fragment createFragment() {
        super.detail = new BuildingListFragment();
        return super.detail;
    }
}
