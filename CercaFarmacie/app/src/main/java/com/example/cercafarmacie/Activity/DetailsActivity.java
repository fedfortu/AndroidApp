package com.example.cercafarmacie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cercafarmacie.R;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String nome = getIntent().getStringExtra("nome");
        String regione = getIntent().getStringExtra("regione");
        String provincia = getIntent().getStringExtra("provincia");
        String comune = getIntent().getStringExtra("comune");
        String indirizzo = getIntent().getStringExtra("indirizzo");
        String cap = getIntent().getStringExtra("cap");
        String descrizioneTipologia = getIntent().getStringExtra("descrizioneTipologia");




        TextView textNome = findViewById(R.id.details_nome);
        TextView textRegione = findViewById(R.id.details_regione);
        TextView textProvincia = findViewById(R.id.details_provincia);
        TextView textComune = findViewById(R.id.details_comune);
        TextView textIndirizzo = findViewById(R.id.details_indirizzo);
        TextView textCap = findViewById(R.id.details_cap);
        TextView textDescrizioneTipologia= findViewById(R.id.details_descrizioneTipologia);



        textNome.setText(nome);
        textRegione.setText(regione);
        textProvincia.setText(provincia);
        textComune.setText(comune);
        textIndirizzo.setText(indirizzo);
        textCap.setText(cap);
        textDescrizioneTipologia.setText(descrizioneTipologia);


    }

    public void onClick (View view) {

        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");
        String comune = getIntent().getStringExtra("comune");
        String nome = getIntent().getStringExtra("nome");

        if (latitude.equals("-") || longitude.equals("-")) {
            Toast.makeText(DetailsActivity.this, R.string.toast_nocoord, Toast.LENGTH_SHORT).show();
        } else{

            Intent intent = new Intent(view.getContext(), MapsActivity.class);
            intent.putExtra("nome", nome);
            intent.putExtra("comune", comune);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            view.getContext().startActivity(intent);

        }
    }
}