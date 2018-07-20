package com.example.luka.fiskalservisapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PretragaNaloga extends AppCompatActivity {

    EditText editPretraga;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> NalogKupac;
    ArrayList<String> NalogSerjskiBr;
    ArrayList<String> NalogKvar;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretraga_naloga);

        editPretraga = (EditText) findViewById(R.id.editPretraga);
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        NalogKupac = new ArrayList<>();
        NalogSerjskiBr = new ArrayList<>();
        NalogKvar = new ArrayList<>();

        editPretraga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              if(!s.toString().isEmpty()) {

                  setAdapter(s.toString());
            }         else  {

                  NalogSerjskiBr.clear();
                  NalogKvar.clear();
                  NalogKupac.clear();
                  recyclerView.removeAllViews();}
            }
        });

    }

    private void setAdapter(final String searchstring) {

        databaseReference.child("Nalog").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                NalogSerjskiBr.clear();
                NalogKvar.clear();
                NalogKupac.clear();
                recyclerView.removeAllViews();
                int counter= 0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                String uid= snapshot.getKey();
                String Kvar = snapshot.child("Kvar").getValue(String.class);
                    String SerijskiBr = snapshot.child("SerijskiBr").getValue(String.class);
                    String Kupac = snapshot.child("kupac").getValue(String.class);


                    if (SerijskiBr.toLowerCase().contains(searchstring.toLowerCase())) {
                         NalogSerjskiBr.add(SerijskiBr);
                         NalogKvar.add(Kvar);
                         NalogKupac.add(Kupac);
                         counter++;
                    } else if(Kvar.toLowerCase().contains(searchstring.toLowerCase())) {
                        NalogSerjskiBr.add(SerijskiBr);
                        NalogKvar.add(Kvar);
                        NalogKupac.add(Kupac);
                        counter++;
                    }
                    if (counter==15)
                        break;
                }
searchAdapter = new SearchAdapter(PretragaNaloga.this, NalogSerjskiBr,NalogKvar,NalogKupac);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
