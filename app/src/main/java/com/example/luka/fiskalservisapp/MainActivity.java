package com.example.luka.fiskalservisapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
   LinearLayout ImgPrijava;
   ImageView ImgArtikl;
   LinearLayout ImgPregled;
   ImageView ImgKupac;
   ImageView ImgNalog;
   ImageView ImgPregledArtikla;
   TextView textGrid;
   LinearLayout ImgPregledNaloga;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Thread t = new Thread() {
         @Override
            public  void run() {
             try {
                 while (!isInterrupted()) {
                     Thread.sleep(1000);
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {

                             TextView tdate = (TextView) findViewById(R.id.textGrid);
                             long date = System.currentTimeMillis();
                             SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                             String  dateString = sdf.format(date);
                             tdate.setText(dateString);



                         }

                     });
                 }
             } catch (InterruptedException e) {
             }
         }
        };
        t.start();

        ImgPregledNaloga = (LinearLayout) findViewById(R.id.ImgPregledNaloga);
        textGrid = (TextView) findViewById(R.id.textGrid);
        ImgPrijava = (LinearLayout) findViewById(R.id.ImgPrijava);
        ImgArtikl = (ImageView) findViewById(R.id.ImgArtikl);
        ImgPregled = (LinearLayout) findViewById(R.id.ImgPregled);
        ImgKupac = (ImageView) findViewById(R.id.ImgKupac);
        ImgNalog = (ImageView) findViewById(R.id.ImgNalog);
        ImgPregledArtikla = (ImageView) findViewById(R.id.ImgPregleArtikla);

       ImgPrijava.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent nalog = new Intent(MainActivity.this,PrijavaNaloga.class);
               startActivity(nalog);
           }
       });
        ImgPregledNaloga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nalog1 = new Intent(MainActivity.this,PretragaNaloga.class);
                startActivity(nalog1);
            }
        });

       ImgPregled.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent pregled = new Intent(MainActivity.this,PregledNaloga.class);
               startActivity(pregled);
           }
       });
    }

        }



