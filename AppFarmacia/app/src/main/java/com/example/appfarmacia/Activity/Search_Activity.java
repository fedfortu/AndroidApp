package com.example.appfarmacia.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.appfarmacia.Activity.Adapter.Adapter;
import com.example.appfarmacia.Database.RDatabase;
import com.example.appfarmacia.Model.Farmacie;
import com.example.appfarmacia.R;

import java.util.ArrayList;
import java.util.List;

public class Search_Activity extends AppCompatActivity{

    private List<Farmacie> farmacie =new ArrayList<>();
    private Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);

        adapter = new Adapter(farmacie);
        RecyclerView list = findViewById(R.id.search_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);








      //  final String comune = getIntent().getStringExtra("query");


        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Farmacie> data = RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().findFarmacieByComune(2);
                farmacie.addAll(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(adapter != null) adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();



    }


}
