package org.citytechmaps.ui.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.citytechmaps.R;
public class BuildingRoomFragment extends Fragment {

    private long id = -1;

    public BuildingRoomFragment() {
        // Required empty public constructor
    }

    public static BuildingRoomFragment newInstance(long id) {
        BuildingRoomFragment fragment = new BuildingRoomFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getLong("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_building_room, container, false);

        return rootView;
    }
}
