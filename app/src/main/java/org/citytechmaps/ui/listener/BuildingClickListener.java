package org.citytechmaps.ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import org.citytechmaps.ui.detail.BuildingDetailActivity;

public class BuildingClickListener implements OnClickListener{
    private Context oontext;
    private long id;

    public BuildingClickListener(Context context, long id) {
        super();
        this.id = id;
        this.oontext = context;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(oontext, BuildingDetailActivity.class);
        i.putExtra("id", id);
        oontext.startActivity(i);
    }
}
