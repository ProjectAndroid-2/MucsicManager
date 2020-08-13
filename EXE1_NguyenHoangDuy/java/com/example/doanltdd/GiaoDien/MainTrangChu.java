package com.example.doanltdd.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.doanltdd.R;

public class MainTrangChu extends AppCompatActivity {
    ImageView btnThongTinBieuDien,btnNhacSy,btnCaSy,btnBaiHat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setController();
        setEvent();
    }

    private void setEvent() {
        btnNhacSy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTrangChu.this,MainNhacSy.class);
                startActivity(intent);
            }
        });
    }

    private void setController() {
        btnThongTinBieuDien=findViewById(R.id.btnThongTinBieuDien);
        btnBaiHat=findViewById(R.id.btnBaiHat);
        btnNhacSy=findViewById(R.id.btnNhacSy);
        btnCaSy=findViewById(R.id.btnCaSy);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}