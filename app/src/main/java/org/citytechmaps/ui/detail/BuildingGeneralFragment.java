package org.citytechmaps.ui.detail;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.citytechmaps.R;
import org.citytechmaps.data.classes.Building;
import org.citytechmaps.data.database.DataManager;

public class BuildingGeneralFragment extends Fragment implements OnMapReadyCallback {

    private long id = -1;
    private SupportMapFragment mapFragment;

    public BuildingGeneralFragment() {
        // Required empty public constructor
    }

    public static BuildingGeneralFragment newInstance(long id) {
        BuildingGeneralFragment fragment = new BuildingGeneralFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_building_general, container, false);

        // Populate views
        TextView tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        TextView tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);

        //tvDescription.setText(DataManager.getInstance(getActivity().getApplicationContext()).queryBuilding(id).getDescription());
        tvAddress.setText(DataManager.getInstance(getActivity().getApplicationContext()).queryBuilding(id).getAddress());

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {

        Building building = DataManager.getInstance(getActivity().getApplicationContext()).queryBuilding(id);

        String name = building.getName();
        double latitude = building.getLatitude();
        double longitude = building.getLongitude();

        LatLng position = new LatLng(40.695419, -73.987518);

        // Set marker on building
        map.addMarker(new MarkerOptions().position(position).title(name));

        // Move camera to school marker
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(position, 15);
        map.animateCamera(location);

        // Enable location
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
    }
}
