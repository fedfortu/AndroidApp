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

            String[] stringaFarmacia = response.split(";");

            for (int i = 0; i < stringaFarmacia.length; i = i + 21) {
                for (int j = 0; j < 21; j++) {

                    Farmacie farmacia = new Farmacie();

                    if (j == 0) {
                        farmacia.setIdFarmacia(stringaFarmacia[j]);
                    } else if (j == 1) {
                        farmacia.setCodiceFarmacia(stringaFarmacia[j]);
                    } else if (j == 2) {
                        farmacia.setIndirizzo(stringaFarmacia[j]);
                    } else if (j == 3) {
                        farmacia.setDescrizioneFarmacia(stringaFarmacia[j]);
                    } else if (j == 4) {
                        farmacia.setPartitaIva(stringaFarmacia[j]);
                    } else if (j == 5) {
                        farmacia.setCap(stringaFarmacia[j]);
                    } else if (j == 6) {
                        farmacia.setCodiceComuneIstat(stringaFarmacia[j]);
                    } else if (j == 7) {
                        farmacia.setDescrizioneComune(stringaFarmacia[j]);
                    } else if (j == 8) {
                        farmacia.setFrazione(stringaFarmacia[j]);
                    } else if (j == 9) {
                        farmacia.setCodiceProvinciaIstat(stringaFarmacia[j]);
                    } else if (j == 10) {
                        farmacia.setSiglaProvincia(stringaFarmacia[j]);
                    } else if (j == 11) {
                        farmacia.setDescrizioneProvincia(stringaFarmacia[j]);
                    } else if (j == 12) {
                        farmacia.setCodiceRegione(stringaFarmacia[j]);
                    } else if (j == 13) {
                        farmacia.setDescrizioneRegione(stringaFarmacia[j]);
                    } else if (j == 14) {
                        farmacia.setDataInizioValidita(stringaFarmacia[j]);
                    } else if (j == 15) {
                        farmacia.setDataFineValidita(stringaFarmacia[j]);
                    } else if (j == 16) {
                        farmacia.setDescrizioneTipologia(stringaFarmacia[j]);
                    } else if (j == 17) {
                        farmacia.setCodiceTipologia(stringaFarmacia[j]);
                    } else if (j == 18) {
                        farmacia.setLatitudine(stringaFarmacia[j]);
                    } else if (j == 19) {
                        farmacia.setLongitudine(stringaFarmacia[j]);
                    } else if (j == 20) {
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

        private void saveDataInDB(final Farmacie farmacie) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RDatabase.getInstance(getApplicationContext())
                            .getFarmacieDao().save(farmacie);
                }
            }).start();
        }
    }


