package com.test.hmapp.GridViwe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.hmapp.R;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private final String[] text;
    private final int[] imageId;

    public GridAdapter(Context context, String[] text, int[] imageId) {
        this.context = context;
        this.text = text;
        this.imageId = imageId;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview =view;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (itemview == null) {
            itemview=layoutInflater.inflate(R.layout.home_view,null);
            ImageView img=(ImageView)itemview.findViewById(R.id.imgView);
            TextView textName=(TextView)itemview.findViewById(R.id.txtName);
            textName.setText(text[position]);
            img.setImageResource(imageId[position]);
        }else {
            itemview = (View) view;
        }
        return itemview;

    }
}
