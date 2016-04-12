package org.citytechmaps.ui.detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import org.citytechmaps.R;
import org.citytechmaps.data.database.DataManager;
import org.citytechmaps.ui.adapter.BuildingDetailPagerAdapter;
import org.citytechmaps.ui.general.GenericTabActivity;
import org.citytechmaps.ui.general.adapter.MenuSection;

public class BuildingDetailActivity extends GenericTabActivity {

    private ViewPager viewPager;
    private BuildingDetailPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long id = getIntent().getLongExtra("id", -1);
        setTitle(DataManager.getInstance(getApplicationContext()).queryBuilding(id).getName());

        // Set up view pager
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new BuildingDetailPagerAdapter(getSupportFragmentManager(), id);
        viewPager.setAdapter(adapter);

        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected MenuSection getSelectedSection() {
        return MenuSection.BUILDINGS;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
