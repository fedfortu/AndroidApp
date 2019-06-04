package com.example.cercafarmacie.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.android.volley.Response;
import com.example.cercafarmacie.Database.RDatabase;
import com.example.cercafarmacie.Model.Farmacia;
import com.example.cercafarmacie.R;
import com.example.cercafarmacie.Utility.RequestService;
import com.example.cercafarmacie.Utility.Settings;
import com.example.cercafarmacie.Utility.VolleyRequest;
import com.opencsv.CSVReader;

import android.view.View;
import android.widget.EditText;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private List<Farmacia> farmacie = new ArrayList<>();

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null) return;
            String response = intent.getStringExtra(RequestService.EXTRA_SERVER_RESPONSE);
            if (response == null) return;

        }

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onClick(View view) {
        View parentView = (View) view.getParent();
         EditText editText = parentView.findViewById(R.id.editText);
        String query = editText.getText().toString();
        if (query != "") {
            Intent intent = new Intent(MainActivity.this, ResearchActivity.class);
            intent.putExtra("query", query);
        startActivity(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // If is the first time you open the app, do a HTTP request to download the data
        clearDataFromDB();

        downloadData();

    }


    private void downloadData() {


        VolleyRequest.getInstance(getApplicationContext()).downloadFarmacie(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] rows = response.split("\r\n");

                for(int i= 0; i <rows.length; i++) {

                    String[] stringaFarmacia = rows[i].split(";");

                        Farmacia farmacia = new Farmacia();

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

                        farmacie.add(farmacia);
                    }

            saveDataInDB(farmacie);
     /*   LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(myReceiver, new IntentFilter(RequestService.FILTER_REQUEST_DOWNLOAD));

        // Http request by URLConnection
        Intent intentService = new Intent(getApplicationContext(), RequestService.class);
        intentService.putExtra(RequestService.REQUEST_ACTION, RequestService.REQUEST_DOWNLOAD);
        startService(intentService);
        */
            }
        });
    }



    private void clearDataFromDB() {

        farmacie.clear();

        // Delete by RoomDatabase

        new Thread(new Runnable() {
            @Override
            public void run() {
                RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().deleteAll();
            }
        }).start();

    }


    private void saveDataInDB(final List<Farmacia> farmacia) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().saveAll(farmacia);
            }
        }).start();
    }


    private void loadDataFromDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Farmacia> data = RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().getAllFarmacie();
                farmacie.addAll(data);

            }
        }).start();
    }
}