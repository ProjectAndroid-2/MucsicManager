package com.example.doanltdd.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanltdd.GiaoDien.MainNhacSy;
import com.example.doanltdd.Model.NhacSy;
import com.example.doanltdd.R;

import java.util.ArrayList;
import java.util.Locale;

public class CusttomAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<NhacSy> data;
    ArrayList<NhacSy> data_DS;

    public CusttomAdapter(Context context, int layout, ArrayList<NhacSy> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        this.data_DS = new ArrayList<NhacSy>();
        this.data_DS.addAll(data);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(layout,null);
        TextView tvMaNhacSy = (TextView) convertView.findViewById(R.id.tvMaNS);
        TextView tvTenNhacSy=(TextView) convertView.findViewById(R.id.tvTenNS);
        ImageView info_signature=(ImageView) convertView.findViewById(R.id.info_signature);
        final NhacSy nhacSy =data.get(position);

        tvMaNhacSy.setText(nhacSy.getMaNS());
        tvTenNhacSy.setText(nhacSy.getTenNS());
        Bitmap bitmapToImage = BitmapFactory.decodeByteArray(nhacSy.getImgSignature(), 0, nhacSy.getImgSignature().length);
        info_signature.setImageBitmap(bitmapToImage);



        return convertView;
    }
    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length()==0){
            data.addAll(data_DS);
        }
        else {
            for (NhacSy model : data_DS){
                if (model.getMaNS().toLowerCase(Locale.getDefault())
                        .contains(charText)  ){
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
