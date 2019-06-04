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


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Farmacia> farmacie = new ArrayList<>();

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null) return;
            String response = intent.getStringExtra(RequestService.EXTRA_SERVER_RESPONSE);
            if (response == null) return;

            //todo parsare risposta




            //   swipeRefreshLayout.setRefreshing(false);

            // if (adapter != null) adapter.notifyDataSetChanged();
         /*   for (Farmacia f : farmacie) {
                saveDataInDB(f);

            }
*/
        }

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
        clearDataFromDB();

        downloadData();

    }


    private void downloadData() {


        VolleyRequest.getInstance(getApplicationContext()).downloadFarmacie(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List resultList = new ArrayList();
                InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));


                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                try{
                    String csvLine;
                    while ((csvLine = reader.readLine()) != null) {
                        String[] stringaFarmacia = csvLine.split(";");

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

                        saveDataInDB(farmacia);
                    }
                }
                catch (IOException ex) {
                    throw new RuntimeException("Error in reading CSV file: "+ex);
                }
                finally {
                    try {
                        stream.close();
                    }
                    catch (IOException e) {
                        throw new RuntimeException("Error while closing input stream: "+e);
                    }
                }


                    }
            });
        /*
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(myReceiver, new IntentFilter(RequestService.FILTER_REQUEST_DOWNLOAD));

        // Http request by URLConnection
        Intent intentService = new Intent(getApplicationContext(), RequestService.class);
        intentService.putExtra(RequestService.REQUEST_ACTION, RequestService.REQUEST_DOWNLOAD);
        startService(intentService);
        */
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


    private void saveDataInDB(final Farmacia farmacia) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RDatabase.getInstance(getApplicationContext())
                        .getFarmacieDao().save(farmacia);
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