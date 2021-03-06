package org.citytechmaps.ui.general.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.citytechmaps.R;

public class MenuDrawerListAdapter extends BaseAdapter {

    private String[] menuArray;
    private int selectedIndex;

    public MenuDrawerListAdapter(String[] menuArray) {
        this.menuArray = menuArray;
    }

    @Override
    public int getCount() {
        return menuArray.length;
    }

    @Override
    public String getItem(int position) {
        return menuArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Context context = parent.getContext();
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
            holder.sectionLabel = (TextView) convertView.findViewById(R.id.tvDrawerListItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sectionLabel.setText(getItem(position));
        holder.sectionLabel.setTextColor(context.getResources().getColor(
                selectedIndex == position ? R.color.colorAccent : R.color.colorPrimary));
        return convertView;
    }

    public void setSelectedIndex(int position) {
        selectedIndex = position;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView sectionLabel;
    }
}
