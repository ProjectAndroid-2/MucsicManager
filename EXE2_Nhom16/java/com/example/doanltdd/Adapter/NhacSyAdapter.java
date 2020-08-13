package com.example.doanltdd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanltdd.DataBase.DBNhacSy;
import com.example.doanltdd.Model.NhacSy;
import com.example.doanltdd.R;

import java.util.ArrayList;
import java.util.Locale;

public class NhacSyAdapter extends RecyclerView.Adapter<NhacSyAdapter.ViewHolder> {
    ArrayList<NhacSy> dataNhacSy;
    ArrayList<NhacSy>data_DS;
    Context context;

    public NhacSyAdapter(ArrayList<NhacSy> dataNhacSy, ArrayList<NhacSy> data_DS, Context context) {
        this.dataNhacSy = dataNhacSy;
        this.data_DS = data_DS;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.txtMaNS.setText(dataNhacSy.get(position).getMaNS());
        holder.txtxTenNS.setText(dataNhacSy.get(position).getTenNS());
        final NhacSy nhacSy =dataNhacSy.get(position);
        holder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DBNhacSy dbNhacSy = new DBNhacSy(context);
//                dbNhacSy.Xoa(nhacSy);
//
//                MainActivityShowNhacSy danhSach = (MainActivityShowNhacSy)context;
//                danhSach.CapnhapDL();
                Toast.makeText(view.getContext(), "Xem Chi Tiết", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBNhacSy dbNhacSy=new DBNhacSy(context);
                dbNhacSy.Xoa(nhacSy);
               //MainActivityShowNhacSy mainActivityShowNhacSy=(MainActivityShowNhacSy)context;
               //mainActivityShowNhacSy.CapnhapDL();
              Toast.makeText(view.getContext(), "Đã Xóa", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataNhacSy.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaNS,txtxTenNS;
        Button btnChiTiet;
        ImageButton btnXoa;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           txtMaNS=(TextView) itemView.findViewById(R.id.tvMaNS);
           txtxTenNS=(TextView) itemView.findViewById(R.id.tvTenNS);
           btnChiTiet=(Button) itemView.findViewById(R.id.btnChiTiet);
           btnXoa=(ImageButton) itemView.findViewById(R.id.btnXoa);
       }
   }
    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        dataNhacSy.clear();
        if (charText.length()==0){
            dataNhacSy.addAll(data_DS);
        }
        else {
            for (NhacSy model : data_DS){
                if (model.getMaNS().toLowerCase(Locale.getDefault())
                        .contains(charText)  ){
                    dataNhacSy.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
