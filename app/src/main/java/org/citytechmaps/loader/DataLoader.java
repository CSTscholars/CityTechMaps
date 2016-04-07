package org.citytechmaps.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Abstract class used for querying a specific data
 */
public class DataLoader<E> extends AsyncTaskLoader<E> {
    private E data;

    public DataLoader(Context context) { super(context); }

    @Override
    public E loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (data != null) {
            deliverResult(data);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(E data) {
        this.data = data;
        if (isStarted())
            super.deliverResult(data);
    }
}
