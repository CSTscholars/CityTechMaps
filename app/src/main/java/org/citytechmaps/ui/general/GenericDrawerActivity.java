package org.citytechmaps.ui.general;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.citytechmaps.R;
import org.citytechmaps.ui.general.adapter.MenuSection;
import org.citytechmaps.ui.list.BuildingListActivity;

public abstract class GenericDrawerActivity extends AppCompatActivity {

    protected Fragment detail;
    private ListView lvDrawer;
    private DrawerAdapter drawerAdapter;
    public ActionBarDrawerToggle drawerToggle;
    public DrawerLayout drawerLayout;

    // Whether the current activity is the top of the hierarchy.
    private boolean isTopLevel;

    private static boolean drawerOpened = false;

    public interface ActionOnCloseListener {
        void actionOnClose();

    }

    public ActionOnCloseListener actionOnCloseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTopLevel = false;
    }

    // Override and set to true when applicable
    public void setAsTopLevel() {
        isTopLevel = true;

        enableDrawerIndicator();
    }

    // Set up drawer toggle actions
    public void setupDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        lvDrawer = (ListView) findViewById(R.id.lvDrawer);
        addDrawerItems();
        lvDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToNavDrawerItem(position);
                drawerLayout.closeDrawers();
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
                if (actionOnCloseListener != null) {
                    actionOnCloseListener.actionOnClose();
                    actionOnCloseListener = null;
                }
            }
        };

        // Enable the "hamburger" bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Enable menu button to toggle drawer
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.setDrawerListener(drawerToggle);

        // Automatically open drawer on launch
        if (!drawerOpened) {
            drawerLayout.openDrawer(Gravity.LEFT);
            drawerOpened = true;
        }
    }

    // Go to nav drawer selection
    private void goToNavDrawerItem(int itemId) {
        Intent intent;

        switch (itemId) {
            case 0: // Buildings
                intent = new Intent(GenericDrawerActivity.this, BuildingListActivity.class);
                break;

            default:
                return;
        }

        // Clear back stack whenever a nav drawer is selected
        // VERY IMPORTANT
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        final Intent finalIntent = intent;
        startActivity(finalIntent);

        overridePendingTransition(0, 0);
    }

    // Set up drawer menu options
    private void addDrawerItems() {
        String[] menuItems = getResources().getStringArray(R.array.drawer_list_item);

        drawerAdapter = new DrawerAdapter(getApplicationContext(), R.layout.drawer_list_item, menuItems);
        lvDrawer.setAdapter(drawerAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    public void enableDrawerIndicator() {
        drawerToggle.setDrawerIndicatorEnabled(true);
    }

    public void disableDrawerIndicatorEnabled() {
        drawerToggle.setDrawerIndicatorEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerAdapter != null) {
            drawerAdapter.setSelectedIndex(getSelectedSection().menuListPosition);
        }
    }

    protected abstract MenuSection getSelectedSection();

    // Handle toggle state sync across configuration changes (rotation)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Navigation drawer item selected
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Home and/or menu item selected
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.about:
                //TODO: AboutDialogFragment

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            String title = item.getTitle().toString();
            Spannable newTitle = new SpannableString((title));
            newTitle.setSpan(new ForegroundColorSpan(Color.WHITE), 0, newTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(newTitle);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // Close drawer if back is pressed while drawer is open.
        if (!isTopLevel && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else if (isTopLevel && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Open drawer if back is pressed, this is a top level activity, and  the drawer is closed.
            drawerLayout.openDrawer(GravityCompat.START);
        } else if (isTopLevel && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //Close if back is pressed, this is a top level activity and the drawer is open
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public Fragment getDetail() {
        return detail;
    }

    public class DrawerAdapter extends ArrayAdapter<String> {
        Context context;
        int layoutResourceId;
        String[] items;
        int selectedIndex;

        public DrawerAdapter(Context context, int layoutResourceId, String[] items) {
            super(context, layoutResourceId, items);
            this.context = context;
            this.layoutResourceId = layoutResourceId;
            this.items = items;
        }

        public void setSelectedIndex(int selectedIndex) {
            this.selectedIndex = selectedIndex;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ItemHolder holder;

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layoutResourceId, parent, false);

                holder = new ItemHolder();
                holder.tvDrawerListItem = (TextView) view.findViewById(R.id.tvDrawerListItem);

                view.setTag(holder);
            } else {
                holder = (ItemHolder) view.getTag();
            }

            holder.tvDrawerListItem.setText(items[position]);
            holder.tvDrawerListItem.setTextColor(getResources().getColor(position == selectedIndex ? R.color.colorAccent : R.color.list_text));

            return view;
        }

        class ItemHolder {
            TextView tvDrawerListItem;
        }
    }
}
