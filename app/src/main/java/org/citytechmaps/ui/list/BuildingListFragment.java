package org.citytechmaps.ui.list;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.citytechmaps.R;
import org.citytechmaps.data.classes.Building;
import org.citytechmaps.data.database.BuildingCursor;
import org.citytechmaps.loader.BuildingListCursorLoader;

public class BuildingListFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize loader
        getLoaderManager().initLoader(R.id.building_list_fragment, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new BuildingListCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        BuildingListCursorAdapter adapter = new BuildingListCursorAdapter(getActivity(), (BuildingCursor) data);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Remove the cursor
        setListAdapter(null);
    }

    private class BuildingListCursorAdapter extends CursorAdapter {

        private BuildingCursor buildingCursor;

        public BuildingListCursorAdapter(Context context, BuildingCursor cursor) {
            super(context, cursor, 0);
            this.buildingCursor = cursor;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // Get a row view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return inflater.inflate(R.layout.building_list_item, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Get Building for current row
            final Building building = buildingCursor.getBuilding();

            // Get list item views
            TextView tvBuildingName = (TextView) view.findViewById(R.id.tvBuildingName);
            TextView tvBuildingShorthand = (TextView) view.findViewById(R.id.tvBuildingShorthand);

            tvBuildingName.setText(building.getName());
            tvBuildingShorthand.setText(building.getShorthand());
            tvBuildingShorthand.setTextColor(Color.parseColor(building.getColor()));
        }
    }
}
