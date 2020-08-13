package com.example.doanltdd.GiaoDien;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanltdd.Adapter.NhacSyAdapter;
import com.example.doanltdd.DataBase.DBNhacSy;
import com.example.doanltdd.Model.NhacSy;
import com.example.doanltdd.R;

import java.util.ArrayList;

public class MainActivityShowNhacSy extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<NhacSy> arrayList=new ArrayList<>();
    NhacSyAdapter nhacSyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_nhac_sy);
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        setController();
        initView();

    }

    public void initView() {
        DBNhacSy dbNhacSy = new DBNhacSy(this);
        arrayList = dbNhacSy.LayDL();
        CapnhapDL();

    }
    public void CapnhapDL() {
        try {
           DBNhacSy dbNhacSy = new DBNhacSy(this);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,layoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
            nhacSyAdapter=new NhacSyAdapter(dbNhacSy.LayDL(),arrayList,getApplicationContext());
            recyclerView.setAdapter(nhacSyAdapter);

        } catch (Exception ex) {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(MainActivityShowNhacSy.this, "Không có dữ liệu", Toast.LENGTH_LONG).show();
        }

    }
    private void setController() {
        recyclerView =(RecyclerView) findViewById(R.id.studentsList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    nhacSyAdapter.filter("");
                    recyclerView.clearFocus();
                }
                else {
                    nhacSyAdapter.filter(s);
                }
                return true;
            }
        });
        return true;
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mnSave:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnThoat:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);

    }

}
