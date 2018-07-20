package com.example.luka.fiskalservisapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
Context context;
    ArrayList<String> NalogKupac;
    ArrayList<String> NalogSerjskiBr;
    ArrayList<String> NalogKvar;
    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView kvar, SerijskiBroj;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            kvar = itemView.findViewById(R.id.Kvar);
            SerijskiBroj = itemView.findViewById(R.id.SerijskiBroj);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> nalogKupac, ArrayList<String> nalogSerjskiBr, ArrayList<String> nalogKvar) {
        this.context = context;
        NalogKupac = nalogKupac;
        NalogSerjskiBr = nalogSerjskiBr;
        NalogKvar = nalogKvar;
    }



    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_list, parent , false );
        return  new SearchAdapter.SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
      searchViewHolder.SerijskiBroj.setText(NalogSerjskiBr.get(i));
        searchViewHolder.kvar.setText(NalogKvar.get(i));


    }



    @Override
    public int getItemCount() {
        return NalogSerjskiBr.size();
    }
}
