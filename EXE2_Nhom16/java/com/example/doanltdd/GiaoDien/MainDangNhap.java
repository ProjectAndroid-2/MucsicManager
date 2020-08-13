package com.example.doanltdd.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanltdd.R;

public class MainDangNhap extends AppCompatActivity {
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnVanTay, btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);
        setConTroller();
        setEvent();
    }
    private void setEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTaiKhoan.getText().length()!=0&&edtMatKhau.getText().length()!=0)
                {
                    if(edtTaiKhoan.getText().toString().equals("ducduy")&&edtMatKhau.getText().toString().equals("ducduy"))
                    {
                        Toast.makeText(MainDangNhap.this, "Dang Nhap Thanh Cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainDangNhap.this, MainTrangChu.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainDangNhap.this, "Dang Nhap That Bai", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainDangNhap.this, "Moi nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVanTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDangNhap.this, VanTay.class);
                startActivity(intent);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainDangNhap.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát??");
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void setConTroller() {
        edtTaiKhoan=findViewById(R.id.edtTaiKhoan);
        edtMatKhau=findViewById(R.id.edtMatKhau);

        btnDangNhap=findViewById(R.id.btnDangNhap);
        btnThoat=findViewById(R.id.btnThoat);
        btnVanTay=findViewById(R.id.btnVanTay);
    }
}