package com.hym.groupbuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.groupbuy.R;
import com.hym.groupbuy.bean.HomeSortBean;

import java.util.ArrayList;
import java.util.List;

public class HomeSortItemAdapter extends BaseAdapter {

    private List<HomeSortBean> itemData = new ArrayList<>();
    private Context mContext;

    public HomeSortItemAdapter(List<HomeSortBean> itemData, Context mContext) {
        this.itemData = itemData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public Object getItem(int position) {
        return itemData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pager_sort_home, null);
        ImageView imageView = view.findViewById(R.id.iv);
        imageView.setImageResource(itemData.get(position).getIconID());
        TextView textView = view.findViewById(R.id.tv);
        textView.setText(itemData.get(position).getIconName());
        return view;
    }
}
