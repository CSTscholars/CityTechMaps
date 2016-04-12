package org.citytechmaps.ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class FloorClickListener implements OnClickListener{
    private Context context;
    private long id;

    public FloorClickListener(Context context, long id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(context, null/*FloorDetailActivity.class*/);
        i.putExtra("id", id);
        context.startActivity(i);
    }
}
