package org.citytechmaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity {

    private Room room;
    private TextView tvRoomDesc;
    private ImageView ivFloorMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //Set up collapsing toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tlbrRoom);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctlRoom);

        //Retrieve Room object passed from MainActivity
        room = getIntent().getExtras().getParcelable("room");

        //Initialize views
        tvRoomDesc = (TextView) findViewById(R.id.tvRoomDesc);
        ivFloorMap = (ImageView) findViewById(R.id.ivFloorMap);

        if(room != null) {
            //Set toolbar title to room name
            collapsingToolbarLayout.setTitle(room.getName());
            ivFloorMap.setImageDrawable(getDrawable(R.drawable.default_floor_map));
            tvRoomDesc.setText(room.getDescription());
        }

    }
}
