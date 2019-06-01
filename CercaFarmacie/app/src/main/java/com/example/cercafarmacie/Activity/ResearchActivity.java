package com.example.cercafarmacie.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.cercafarmacie.Activity.Adapter.Adapter;
import com.example.cercafarmacie.Database.RDatabase;
import com.example.cercafarmacie.Model.Farmacie;
import com.example.cercafarmacie.R;


import java.util.ArrayList;
import java.util.List;

public class ResearchActivity extends AppCompatActivity {
    private List<Farmacie> farmacie = new ArrayList<>();
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);

        adapter= new Adapter(farmacie);
        RecyclerView list= findViewById(R.id.search_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Farmacie> data= RDatabase.getInstance(getApplicationContext()).getFarmacieDao().findFarmacieByComune("PONTE BUGGIANESE");
                farmacie.addAll(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter != null) adapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }
}

