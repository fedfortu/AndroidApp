package com.example.appfarmacia.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

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

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null) return;
            String response = intent.getStringExtra(RequestService.EXTRA_SERVER_RESPONSE);
            if (response == null) return;

            //todo parsare risposta

            String[] rows = response.split("\n");

            for (int i = 1; i < rows.length; i++) {
                String[] stringaFarmacia = rows[i].split(";");

                Farmacie farmacia = new Farmacie();

                if (stringaFarmacia.length == 21 && stringaFarmacia[15] == "-") {

                    farmacia.setIdFarmacia(stringaFarmacia[0]);
                    farmacia.setCodiceFarmacia(stringaFarmacia[1]);
                    farmacia.setIndirizzo(stringaFarmacia[2]);
                    farmacia.setDescrizioneFarmacia(stringaFarmacia[3]);
                    farmacia.setPartitaIva(stringaFarmacia[4]);
                    farmacia.setCap(stringaFarmacia[5]);
                    farmacia.setCodiceComuneIstat(stringaFarmacia[6]);
                    farmacia.setDescrizioneComune(stringaFarmacia[7]);
                    farmacia.setFrazione(stringaFarmacia[8]);
                    farmacia.setCodiceProvinciaIstat(stringaFarmacia[9]);
                    farmacia.setSiglaProvincia(stringaFarmacia[10]);
                    farmacia.setDescrizioneProvincia(stringaFarmacia[11]);
                    farmacia.setCodiceRegione(stringaFarmacia[12]);
                    farmacia.setDescrizioneRegione(stringaFarmacia[13]);
                    farmacia.setDataInizioValidita(stringaFarmacia[14]);
                    farmacia.setDataFineValidita(stringaFarmacia[15]);
                    farmacia.setDescrizioneTipologia(stringaFarmacia[16]);
                    farmacia.setCodiceTipologia(stringaFarmacia[17]);
                    farmacia.setLatitudine(stringaFarmacia[18]);
                    farmacia.setLongitudine(stringaFarmacia[19]);
                    farmacia.setLocalize(stringaFarmacia[20]);


                    saveDataInDB(farmacia);
                    farmacie.add(farmacia);


                }

            }
        }
        //   swipeRefreshLayout.setRefreshing(false);

        // if (adapter != null) adapter.notifyDataSetChanged();
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadData();

    }

    public void onClick (View view) {
        startActivity(new Intent(MainActivity.this, ResearchActivity.class));
    }

/*
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
*/

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

/*
    private void clearDataFromDB() {

        cities.clear();
        if (adapter != null) adapter.notifyDataSetChanged();

        if (Settings.loadBoolean(getApplicationContext(), Settings.SWITCH_DB, true)) {

            // Delete by SQLiteOpenHelper
            Database.getInstance(getApplicationContext()).delete();

        } else {
            // Delete by RoomDatabase

            new Thread(new Runnable() {
                @Override
                public void run() {
                    RDatabase.getInstance(getApplicationContext())
                            .getCityDao().deleteAll();
                }
            }).start();
        }
    }
    */

    private void saveDataInDB(final Farmacie farmacie){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().save(farmacie);
            }
        }).start();
    }
}

