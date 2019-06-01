package com.example.cercafarmacie.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.example.cercafarmacie.Activity.Adapter.Adapter;
import com.example.cercafarmacie.Database.RDatabase;
import com.example.cercafarmacie.Model.Farmacie;
import com.example.cercafarmacie.R;
import com.example.cercafarmacie.Utility.RequestService;
import com.example.cercafarmacie.Utility.Settings;
import com.opencsv.CSVReader;

import android.view.View;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            String[] stringaFarmacia = null;
            String csvFilename = response;

            CSVReader csvReader = null;
            try {
                csvReader = new CSVReader(new FileReader(csvFilename), ';');

            List content = csvReader.readAll();

            for (Object object : content) {
                stringaFarmacia = (String[]) object;

                Farmacie farmacia = new Farmacie();

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

            csvReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


            //   swipeRefreshLayout.setRefreshing(false);

            // if (adapter != null) adapter.notifyDataSetChanged();
        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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