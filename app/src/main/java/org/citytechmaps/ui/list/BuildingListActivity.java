package org.citytechmaps.ui.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.citytechmaps.ui.general.adapter.MenuSection;
import org.citytechmaps.ui.general.GenericFragmentActivity;

public class BuildingListActivity extends GenericFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Buildings");

        // Top level activity
        setAsTopLevel();
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
