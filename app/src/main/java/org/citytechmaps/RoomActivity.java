package org.citytechmaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private Room room;

    private ImageView ivRoomBG;
    private TextView tvRoomName;
    private TextView tvRoomDesc;
    private SurfaceView sfvFloorMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //Retrieve Room object passed from MainActivity
        room = getIntent().getExtras().getParcelable("room");

        //Initialize views
        ivRoomBG = (ImageView) findViewById(R.id.ivRoomBG);
        tvRoomName = (TextView) findViewById(R.id.tvRoomName);
        tvRoomDesc = (TextView) findViewById(R.id.tvRoomDesc);
        sfvFloorMap = (SurfaceView) findViewById(R.id.sfvFloorMap);

        //Set
        ivRoomBG.setImageDrawable(getDrawable(room.getImg()));
        tvRoomName.setText(room.getName());
        tvRoomDesc.setText(room.getDescription());

        SurfaceHolder holder = sfvFloorMap.getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();

        //Color RED
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        //White background
        canvas.drawColor(Color.WHITE);

        //Get bitmap image of floor map
        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.default_floor_map);

        //Draw bitmap onto canvas
        canvas.drawBitmap(map, null, canvas.getClipBounds(), null);

        //Draw circle onto canvas
        canvas.drawCircle(room.getX(), room.getY(), 20, paint);

        holder.unlockCanvasAndPost(canvas);

        //TODO: Animate circle pulse
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
