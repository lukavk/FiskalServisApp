package com.example.luka.fiskalservisapp;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PregledNaloga extends AppCompatActivity {
 ListView listView;
 FirebaseDatabase database;
 DatabaseReference ref;
 ArrayList<String>list;
 ArrayAdapter<String> adapter;
 Nalog nalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_naloga);


        nalog = new Nalog();
        listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Nalog");
        list =  new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.nalog_info,R.id.nalogInfo,  list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    nalog = ds.getValue(Nalog.class);
                    list.add(nalog.getKupac().toString() + "   " + (nalog.getSerijskiBr().toString()));




                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public  void showInputBox(String oldItem, final int index) {

        final Dialog dialog= new Dialog(PregledNaloga.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);
        TextView txtMessage=(TextView) dialog.findViewById(R.id.txtporuka);
        txtMessage.setText("Update");
        txtMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText = (EditText) dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);
        Button bt = (Button) dialog.findViewById(R.id.btndone);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.set(index,editText.getText().toString());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
dialog.show();

    }
}
