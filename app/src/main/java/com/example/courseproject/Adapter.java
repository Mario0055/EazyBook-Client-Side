package com.example.courseproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<Movies> List=new ArrayList<>();
    private Context context;

    public Adapter(Context context ,ArrayList<Movies> List) {
        this.context = context;
        this.List = List;
    }


    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int i) {
        return List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.adapter_view_layout ,viewGroup ,false);
        String Temp = List.get(i).getImage().toString();
        String Temp2 = "https://image.tmdb.org/t/p/w500";
        String Temp3 = Temp2 + Temp;
        ImageView img=view1.findViewById(R.id.adapter_view_layout_imageView_ID);

        Picasso.with(context).load(Temp3).into(img);
        return view1;
    }
}
