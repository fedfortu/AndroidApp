package com.example.appfarmacia.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.example.appfarmacia.Activity.Adapter.Adapter;
import com.example.appfarmacia.Database.RDatabase;
import com.example.appfarmacia.Model.Farmacie;
import com.example.appfarmacia.R;
import com.example.appfarmacia.Utility.RequestService;
import com.example.appfarmacia.Utility.Settings;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Farmacie> farmacie = new ArrayList<>();

    private Adapter adapter;

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null) return;
            String response = intent.getStringExtra(RequestService.EXTRA_SERVER_RESPONSE);
            if (response == null) return;

            //todo parsare risposta

            String[] stringaFarmacia = response.split(";");

            for (int i = 21; i < stringaFarmacia.length; i = i + 20) {

                Farmacie farmacia = new Farmacie();

                for (int j = i; j < i + 20; j++) {


                    if (j == i) {
                        farmacia.setCodiceFarmacia(stringaFarmacia[j]);
                    } else if (j == i + 1) {
                        farmacia.setIndirizzo(stringaFarmacia[j]);
                    } else if (j == i + 2) {
                        farmacia.setDescrizioneFarmacia(stringaFarmacia[j]);
                    } else if (j == i + 3) {
                        farmacia.setPartitaIva(stringaFarmacia[j]);
                    } else if (j == i + 4) {
                        farmacia.setCap(stringaFarmacia[j]);
                    } else if (j == i + 5) {
                        farmacia.setCodiceComuneIstat(stringaFarmacia[j]);
                    } else if (j == i + 6) {
                        farmacia.setDescrizioneComune(stringaFarmacia[j]);
                    } else if (j == i + 7) {
                        farmacia.setFrazione(stringaFarmacia[j]);
                    } else if (j == i + 8) {
                        farmacia.setCodiceProvinciaIstat(stringaFarmacia[j]);
                    } else if (j == i + 9) {
                        farmacia.setSiglaProvincia(stringaFarmacia[j]);
                    } else if (j == i + 10) {
                        farmacia.setDescrizioneProvincia(stringaFarmacia[j]);
                    } else if (j == i + 11) {
                        farmacia.setCodiceRegione(stringaFarmacia[j]);
                    } else if (j == i + 12) {
                        farmacia.setDescrizioneRegione(stringaFarmacia[j]);
                    } else if (j == i + 13) {
                        farmacia.setDataInizioValidita(stringaFarmacia[j]);
                    } else if (j == i + 14) {
                        farmacia.setDataFineValidita(stringaFarmacia[j]);
                    } else if (j == i + 15) {
                        farmacia.setDescrizioneTipologia(stringaFarmacia[j]);
                    } else if (j == i + 16) {
                        farmacia.setCodiceTipologia(stringaFarmacia[j]);
                    } else if (j == i + 17) {
                        farmacia.setLatitudine(stringaFarmacia[j]);
                    } else if (j == i + 18) {
                        farmacia.setLongitudine(stringaFarmacia[j]);
                    } else if (j == i + 19) {
                        farmacia.setLocalize(stringaFarmacia[j]);
                    }


                    saveDataInDB(farmacia);
                    farmacie.add(farmacia);


                }

            }


            //   swipeRefreshLayout.setRefreshing(false);

            // if (adapter != null) adapter.notifyDataSetChanged();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadData();

    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ResearchActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // If is the first time you open the app, do a HTTP request to download the data
        if (Settings.loadBoolean(getApplicationContext(), Settings.FIRST_TIME, true)) {

            clearDataFromDB();

            downloadData();

        } else {
            // If is not the first time you open the app, get all saved data from Database

            loadDataFromDB();
        }
        Settings.save(getApplicationContext(), Settings.FIRST_TIME, false);
    }


    private void downloadData() {

        // swipeRefreshLayout.setRefreshing(true);

        // Registering the receiver
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(myReceiver, new IntentFilter(RequestService.FILTER_REQUEST_DOWNLOAD));

        // Http request by URLConnection
        Intent intentService = new Intent(getApplicationContext(), RequestService.class);
        intentService.putExtra(RequestService.REQUEST_ACTION, RequestService.REQUEST_DOWNLOAD);
        startService(intentService);


    }


    private void clearDataFromDB() {

        farmacie.clear();
        if (adapter != null) adapter.notifyDataSetChanged();
        {
            // Delete by RoomDatabase

            new Thread(new Runnable() {
                @Override
                public void run() {
                    RDatabase.getInstance(getApplicationContext())
                            .getFarmacieDao().deleteAll();
                }
            }).start();
        }
    }


    private void saveDataInDB(final Farmacie farmacie) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().save(farmacie);
            }
        }).start();
    }


    private void loadDataFromDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Farmacie> data = RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().getAllFarmacie();
                farmacie.addAll(data);

            }
        }).start();
    }
}


