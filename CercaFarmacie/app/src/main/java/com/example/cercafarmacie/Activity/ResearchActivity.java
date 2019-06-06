package com.example.cercafarmacie.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.cercafarmacie.Activity.Adapter.Adapter;
import com.example.cercafarmacie.Database.RDatabase;
import com.example.cercafarmacie.Model.Farmacia;
import com.example.cercafarmacie.R;


import java.util.ArrayList;
import java.util.List;

public class ResearchActivity extends AppCompatActivity{
    private List<Farmacia> farmacie = new ArrayList<>();
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);

        adapter= new Adapter(farmacie);
        RecyclerView list= findViewById(R.id.search_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);


        final String comune = getIntent().getStringExtra("query");


        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Farmacia> data= RDatabase.getInstance(getApplicationContext()).getFarmacieDao().findFarmacieByComune(comune, "-");
                farmacie.addAll(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }).start();
    }
}

