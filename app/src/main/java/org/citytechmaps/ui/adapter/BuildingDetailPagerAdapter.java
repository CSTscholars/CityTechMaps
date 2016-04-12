package org.citytechmaps.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.citytechmaps.ui.detail.BuildingFloorFragment;
import org.citytechmaps.ui.detail.BuildingGeneralFragment;
import org.citytechmaps.ui.detail.BuildingRoomFragment;

public class BuildingDetailPagerAdapter extends FragmentPagerAdapter{

    private long id;

    //Tab titles
    private String[] tabs = { "General", "Floors", "Rooms" };

    public BuildingDetailPagerAdapter(FragmentManager fm, long id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return BuildingGeneralFragment.newInstance(id);

            case 1:
                return BuildingFloorFragment.newInstance(id);

            case 2:
                return BuildingRoomFragment.newInstance(id);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs.length - 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
