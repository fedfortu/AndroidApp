package com.example.cercafarmacie.Utility;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * MobileProgramming2018
 * Created by leonardo on 30/11/2018.
 */
public class VolleyRequest {

    private RequestQueue queue;

    private static VolleyRequest instance = null;

    public static VolleyRequest getInstance(Context context){
        return instance == null ? instance = new VolleyRequest(context) : instance;
    }

    private VolleyRequest(Context context){

        queue = Volley.newRequestQueue(context);
    }

    public void downloadFarmacie(Response.Listener<String> listener){

        StringRequest request = new StringRequest(
                StringRequest.Method.GET,
                "http://www.dati.salute.gov.it/imgs/C_17_dataset_5_download_itemDownload0_upFile.CSV",
                 listener,
                null);
        queue.add(request);
    }
}
