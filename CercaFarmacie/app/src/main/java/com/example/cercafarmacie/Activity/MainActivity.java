package com.example.cercafarmacie.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.android.volley.Response;
import com.example.cercafarmacie.Database.RDatabase;
import com.example.cercafarmacie.Model.Farmacia;
import com.example.cercafarmacie.R;
import com.example.cercafarmacie.Utility.Settings;
import com.example.cercafarmacie.Utility.VolleyRequest;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Farmacia> farmacie = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Settings.loadBoolean(getApplicationContext(), Settings.FIRST_TIME, true)) {
            clearDataFromDB();

            downloadData();
        }
         Settings.save(getApplicationContext(), Settings.FIRST_TIME, false);
    }



    private void showExitDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.add(0, 1, 0, R.string.main_refresh);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()){
            case R.id.main_exit:
                showExitDialog();
                return true;

            case 1:

                Button button = (Button) findViewById(R.id.button);

                button.getBackground().setAlpha(50);
                button.setClickable(false);

                clearDataFromDB();

                downloadData();

                return true;

            default:
                return false;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void downloadData() {


        VolleyRequest.getInstance(getApplicationContext()).downloadFarmacie(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] rows = response.split("\r\n");

                for(int i= 1; i <rows.length; i++) {

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
                Button button = (Button) findViewById(R.id.button);

                button.getBackground().setAlpha(255);
                button.setClickable(true);


            }

        });

    }

    private void clearDataFromDB() {

        farmacie.clear();


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


    @Override
        public void onClick (View view){



        View parentView = (View) view.getParent();
        EditText editText = parentView.findViewById(R.id.editText);
        String query = editText.getText().toString();
        if (query != "") {
            Intent intent = new Intent(MainActivity.this, ResearchActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }

    }

}