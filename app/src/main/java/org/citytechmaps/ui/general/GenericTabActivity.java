package org.citytechmaps.ui.general;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.Toolbar;

import org.citytechmaps.R;

/*
 * Any subclass needs to:
 *  - override onCreate() to set title
 *  - override createFragment() for detail fragments
 */
public abstract class GenericTabActivity extends GenericDrawerActivity{

    protected Fragment detail;
    protected SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab);

        //Include Toolbar so sliding drawer can go over it.
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Set up tabs
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.sliding_tab_layout, R.id.text);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int width = size.x;

        slidingTabLayout.setMinimumWidth(width);
        slidingTabLayout.setDistributeEvenly(true);

        setTitle("City Tech Maps");
        setupDrawer();
    }
}
