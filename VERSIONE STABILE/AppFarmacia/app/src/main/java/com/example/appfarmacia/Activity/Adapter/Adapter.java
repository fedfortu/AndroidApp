package com.example.appfarmacia.Activity.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appfarmacia.Model.Farmacie;
import com.example.appfarmacia.R;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Farmacie> data;

    public Adapter(List<Farmacie> data){
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Farmacie farmacie = data.get(i);
        viewHolder.title.setText(farmacie.getDescrizioneFarmacia());
        viewHolder.subtitle.setText(farmacie.getIndirizzo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Use ViewHolder Pattern
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;

        ViewHolder(@NonNull View view) {
            super(view);

            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);

            // Define the click event on item
          /*  view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Open another Activity and pass to it the right city
                    Farmacie farmacie = data.get(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), Search_Activity.class);
                    intent.putExtra("RegionName", farmacie.getDescrizioneFarmacia());
                    intent.putExtra("CityName", farmacie.getDescrizioneComune());
                    intent.putExtra("latitude", farmacie.getLatitudine());
                    intent.putExtra("longitude", farmacie.getLongitudine());
                    v.getContext().startActivity(intent);
                }
            }); */
         }
    }
}
