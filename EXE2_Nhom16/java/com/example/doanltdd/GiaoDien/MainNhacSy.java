package com.example.doanltdd.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanltdd.Adapter.CusttomAdapter;
import com.example.doanltdd.DataBase.DBNhacSy;
import com.example.doanltdd.Model.NhacSy;
import com.example.doanltdd.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainNhacSy extends AppCompatActivity {
    TextView txtMaNS;
    TextView txtTenNS;
    Button btnThem,btnXoa,btnSua,btnClear;
    ListView lvDanhSach;

    ImageButton img_signature;
    PaintView paintView;
    ArrayList<NhacSy> data_qlNhacSy=new ArrayList<>();
    CusttomAdapter customAdapter_qlnhacsy;
    int vt=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nhac_sy);
        setController();
        setEvent();
    }
    private void setEvent() {

        customAdapter_qlnhacsy = new CusttomAdapter(MainNhacSy.this,R.layout.lv_nhacsy,data_qlNhacSy);
        lvDanhSach.setAdapter(customAdapter_qlnhacsy);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMaNS.getText().length()!=0 &&txtTenNS.getText().length()!=0)
                {
                    NhacSy nhacSy= new NhacSy();
                    nhacSy.setMaNS(txtMaNS.getText().toString());
                    nhacSy.setTenNS(txtTenNS.getText().toString());
                    nhacSy.setImgSignature(getByteBitmap());
                    data_qlNhacSy.add(nhacSy);
                    customAdapter_qlnhacsy.notifyDataSetChanged();
                    DBNhacSy dbNhacSy = new DBNhacSy(getApplicationContext());
                    NhacSy nhacSy1 = getNhacSy();
                    dbNhacSy.Them(nhacSy1);
                }else
                {
                    Toast.makeText(MainNhacSy.this,"Bạn nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }


            }
        });




        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainNhacSy.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có Muốn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_qlNhacSy.remove(position);
                        customAdapter_qlnhacsy.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog dialog= builder.create();
                dialog.show();
                return false;
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhacSy nhacSy=data_qlNhacSy.get(position);

                txtMaNS.setText(nhacSy.getMaNS());
                txtTenNS.setText(nhacSy.getTenNS());
                vt=position;
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainNhacSy.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có Muốn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_qlNhacSy.remove(vt);
                        customAdapter_qlnhacsy.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog dialog= builder.create();
                dialog.show();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhacSy nhacSy=data_qlNhacSy.get(vt);
                nhacSy.setMaNS(txtMaNS.getText().toString());
                nhacSy.setTenNS(txtTenNS.getText().toString());
                customAdapter_qlnhacsy.notifyDataSetChanged();
                DBNhacSy dbNhacSy = new DBNhacSy(getApplicationContext());
                dbNhacSy.Sua(nhacSy);

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtMaNS.setText("");
                txtTenNS.setText("");
            }
        });
        img_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });
    }

    public void createPopupDialog(){
        final androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(MainNhacSy.this);
        View view=getLayoutInflater().inflate(R.layout.signature,null);
        paintView =view.findViewById(R.id.customSign);
      Button  bt_finish=view.findViewById(R.id.bt_finish);

        builder.setView(view);
        final androidx.appcompat.app.AlertDialog show= builder.show();
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_signature.setImageBitmap(getSignature());
                show.dismiss();
            }
        });


    }
    private Bitmap getSignature(){
        return paintView.getBitmap();
    }
    private byte[] getByteBitmap(){
        try {
            Bitmap photo = paintView.getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
            return bos.toByteArray();
        }catch (Exception e){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    private NhacSy getNhacSy() {
        NhacSy nhacSy = new NhacSy();
        nhacSy.setMaNS(txtMaNS.getText().toString());
        nhacSy.setTenNS(txtTenNS.getText().toString());
        return nhacSy;
    }
    private void setController() {
        txtMaNS=findViewById(R.id.edtMans);
        txtTenNS=findViewById(R.id.edtTenns);

        btnThem=findViewById(R.id.btnThem);
        btnXoa=findViewById(R.id.btnXoa);
        btnSua=findViewById(R.id.btnSua);
        btnClear=findViewById(R.id.btnClear);
        lvDanhSach=findViewById(R.id.lvShow);

        img_signature=findViewById(R.id.bt_signature);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnSave:
                Log.d("test", "Save");
                break;

            case R.id.mnChuyen:
                Log.d("test", "Open");
                Intent intent = new Intent(this, MainActivityShowNhacSy.class);
                startActivity(intent);
                break;

            case R.id.mnThoat:
                AlertDialog.Builder builder= new AlertDialog.Builder(MainNhacSy.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát không??");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog dialog= builder.create();
                dialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}