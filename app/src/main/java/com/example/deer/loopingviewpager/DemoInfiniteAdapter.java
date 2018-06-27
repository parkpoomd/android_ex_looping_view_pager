package com.example.deer.loopingviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.loopingviewpager.LoopingPagerAdapter;

import java.util.ArrayList;

public class DemoInfiniteAdapter extends LoopingPagerAdapter {

    private static final int VIEW_TYPE_NORMAL = 100;
    private static final int VIEW_TYPE_SPECIAL = 101;

    public DemoInfiniteAdapter(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected int getItemViewType(int listPosition) {

        int viewType = (Integer) itemList.get(listPosition);
        if (viewType == 0) return VIEW_TYPE_SPECIAL;
        return VIEW_TYPE_NORMAL;
    }

    // This method will be triggered if the item View has not been inflated before.
    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        if (viewType == VIEW_TYPE_SPECIAL)
            return LayoutInflater.from(context).inflate(R.layout.item_special, container, false);
        return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
    }

    // Bind your data with your item View here.
    // Below is just an example in the demo app.
    // You can assume convertView will not be null here.
    // You may also consider using a ViewHolder pattern.
    @Override
    protected void bindView(View convertView, final int listPosition, int viewType) {
        if (viewType == VIEW_TYPE_SPECIAL) return;
        convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
        TextView description = convertView.findViewById(R.id.description);
        description.setText(String.valueOf(itemList.get(listPosition)));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onClick : " + itemList.get(listPosition), Toast.LENGTH_LONG).show();
            }
        });
    }

    private int getBackgroundColor(int number) {
        switch (number) {
            case 0:
                return android.R.color.holo_red_light;
            case 1:
                return android.R.color.holo_orange_light;
            case 2:
                return android.R.color.holo_green_light;
            case 3:
                return android.R.color.holo_blue_light;
            case 4:
                return android.R.color.holo_purple;
            case 5:
                return android.R.color.black;
            default:
                return android.R.color.black;
        }
    }
}
