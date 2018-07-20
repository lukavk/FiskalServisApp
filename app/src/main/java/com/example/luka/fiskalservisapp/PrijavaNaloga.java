package com.example.luka.fiskalservisapp;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrijavaNaloga extends AppCompatActivity {
     TextView txtStatus;
    TextView txtPrijava;
    TextView txtKupac;
    TextView txtDatum;
    TextView txtUredaj;
    TextView txtSerijskiBr;
    TextView txtKvar;
    EditText editPrijava;
    EditText editKupac;
    EditText editDatum;
    EditText editUredaj;
    EditText editSerijskiBr;
    EditText editKvar;
    EditText editStatus;
    Button btnPosalji;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava_naloga);





        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtKupac = (TextView) findViewById(R.id.txtKupac);
        txtUredaj = (TextView) findViewById(R.id.txtUredaj);
        txtDatum = (TextView) findViewById(R.id.txtDatum);
        txtKvar = (TextView) findViewById(R.id.txtKvar);
        txtSerijskiBr = (TextView) findViewById(R.id.txtSerijskiBr);
         editStatus = (EditText) findViewById(R.id.editStatus);
        editKupac = (EditText) findViewById(R.id.editKupac);
        editDatum = (EditText) findViewById(R.id.editDatum);
        editUredaj = (EditText) findViewById(R.id.editUredaj);
        editSerijskiBr = (EditText) findViewById(R.id.editSerijskiBr);
        editKvar = (EditText) findViewById(R.id.editKvar);
        btnPosalji = (Button) findViewById(R.id.btnPosalji);



       final FirebaseDatabase database = FirebaseDatabase.getInstance();
       final DatabaseReference table_user = database.getReference("Nalog");

       btnPosalji.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final ProgressDialog mDialog = new ProgressDialog(PrijavaNaloga.this);
               mDialog.setMessage("Molimo Pricekajte");

               table_user.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       if(dataSnapshot.child(editSerijskiBr.getText().toString()).exists()) {
                           mDialog.dismiss();
                           Toast.makeText(PrijavaNaloga.this, "Nalog vec postoji", Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           mDialog.dismiss();
                           Nalog nalog = new Nalog(editKvar.getText().toString(),editSerijskiBr.getText().toString(),editDatum.getText().toString()
                           ,editKupac.getText().toString(),editStatus.getText().toString(),editUredaj.getText().toString());
                           table_user.child(editSerijskiBr.getText().toString()).setValue(nalog);
                           Toast.makeText(PrijavaNaloga.this, "Prijava uspjesna", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
           }
       });
    }
}
