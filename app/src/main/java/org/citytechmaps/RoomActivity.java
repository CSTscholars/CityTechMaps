package org.citytechmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RoomActivity extends AppCompatActivity {

    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        room = savedInstanceState.getParcelable("room");
    }
}
