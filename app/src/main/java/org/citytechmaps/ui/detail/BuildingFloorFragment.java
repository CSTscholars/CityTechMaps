package org.citytechmaps.ui.detail;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.citytechmaps.R;
import org.citytechmaps.data.classes.Floor;
import org.citytechmaps.data.database.BuildingToFloorCursor;
import org.citytechmaps.loader.FloorListCursorLoader;
import org.citytechmaps.ui.listener.FloorClickListener;

public class BuildingFloorFragment extends ListFragment implements LoaderCallbacks<Cursor>{

    private FloorListCursorAdapter adapter;

    public static BuildingFloorFragment newInstance(long id) {
        BuildingFloorFragment fragment = new BuildingFloorFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(R.id.building_floor_loader, getArguments(), this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //Load the building Id
        long buildingId = -1;
        if (args != null) {
            buildingId = args.getLong("id");
        }

        return new FloorListCursorLoader(getActivity(), buildingId);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter = new FloorListCursorAdapter(getActivity(), (BuildingToFloorCursor) data);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Remove the cursor
        setListAdapter(adapter);
    }

    private class FloorListCursorAdapter extends CursorAdapter {

        private BuildingToFloorCursor btfCursor;

        public FloorListCursorAdapter(Context context, BuildingToFloorCursor cursor) {
            super(context, cursor, 0);
            this.btfCursor = cursor;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            Log.d("BuildingFloorFragment", "list item created");
            // Get a row view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return inflater.inflate(R.layout.floor_list_item, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Get Floor for current row
            final Floor floor = btfCursor.getBuildingToFloor().getFloor();

            Log.d("BuildingFloorFragment", "Floor ID: " + floor.getId());

            // Get list item views
            LinearLayout llListItem = (LinearLayout) view.findViewById(R.id.llListItem);
            TextView tvFloorNumber = (TextView) view.findViewById(R.id.tvFloorNumber);

            tvFloorNumber.setText(floorName(floor.getNumber()));

            // Set click listener
            // TODO: llListItem.setOnClickListener(new FloorClickListener(context, floor.getId()));
        }

        private String floorName(String number) {
            switch (number) {
                case "G":
                    return "Ground Floor";
                case "1":
                    return "1st Floor";
                default:
                    return String.format("%sth Floor", number);
            }
        }
    }
}
