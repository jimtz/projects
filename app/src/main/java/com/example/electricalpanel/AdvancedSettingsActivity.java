package com.example.electricalpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Layout;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import java.util.Random;



public class AdvancedSettingsActivity extends AppCompatActivity {

    protected static TextView text;
    public static  ImageButton  green;
    public static boolean open=false;
    static ArrayList<String> kitchen_ar =new ArrayList<String>();
    static ArrayList<String> sofa_ar =new ArrayList<String>();
    static ArrayList<String> bed_ar =new ArrayList<String>();
    static ArrayList<String> devices_ar =new ArrayList<String>();
    static ArrayList<String> bath_ar =new ArrayList<String>();
    static ArrayList<String> sound_ar =new ArrayList<String>();
    static ArrayList<String> shower_ar =new ArrayList<String>();
    static ArrayList<String> power_ar =new ArrayList<String>();

    public static LinearLayout bath2 ;
    public static LinearLayout bed2 ;
    public static LinearLayout sal2 ;
    public static LinearLayout kitch2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_settings);


        bath2 = findViewById(R.id.bath2);
        bed2 = findViewById(R.id.bed2);
        sal2 = findViewById(R.id.saloon2);
        kitch2 =findViewById(R.id.kitchen2);
        if(!open) {
            open = true;
            overridePendingTransition(0,0);
            onBackPressed();

        }
        color_button(bath2,MainActivity.cbath,"bath");
        color_button(bed2,MainActivity.cbed,"bed");
        color_button(sal2,MainActivity.csal,"sal");
        color_button(kitch2,MainActivity.ckitch,"kitch");



        text = (TextView) findViewById(R.id.text);
        ImageButton powerBtn = (ImageButton) findViewById(R.id.powerBtn);
        if(MainActivity.b_lights)
            text.setText(MainActivity.texting()+"Consumption(kW) Green:Low  Orange:Medium  Red:High");
        else
            text.setText("");


        ImageButton home = (ImageButton) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(0,0);

            }
        });


        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.b_lights){
                    MainActivity.b_lights=false;
                    MainActivity.b_bathroom = false;
                    MainActivity.b_bed = false;
                    MainActivity.b_sofa = false;
                    MainActivity.b_kitchen = false;
                    MainActivity.b_therm=false;
                    MainActivity.b_furn=false;
                    text.setText("");
                    MainActivity.text.setText("");
                    MainActivity.change(false);
                }else{
                    MainActivity.b_lights=true;
                    MainActivity.b_bathroom = true;
                    MainActivity.b_bed = true;
                    MainActivity.b_sofa = true;
                    MainActivity.b_kitchen = true;
                    text.setText(MainActivity.texting()+"Consumption(kW) Green:Low  Yellow:Medium  Red:High");
                    MainActivity.text.setText(text.getText());
                    MainActivity.change(true);
                }


            }
        });



        green = (ImageButton) findViewById(R.id.echo);
        if(MainActivity.run2)
            green.setBackgroundResource(R.drawable.soundcloud22);
        else
            green.setBackgroundResource(R.drawable.soundcloud);

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.b_lights){
                    if (MainActivity.run2) {
                        MainActivity.run2 = false;
                        text.setText("Sound notifications OFF");
                        green.setBackgroundResource(R.drawable.soundcloud);
                    } else {
                        MainActivity.run2 = true;
                        text.setText("Sound notifications ON\nNow you will be notified on\nLow or High energy consumption");
                        green.setBackgroundResource(R.drawable.soundcloud22);
                    }
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            System.out.println("Can't sleep");
                        }
                        text.setText(MainActivity.texting()+"Consumption(kW) Green:Low  Yellow:Medium  Red:High");
                    }
                }).start();
             }
            }
        });





        ImageButton recorder2 = (ImageButton) findViewById(R.id.record2);
        recorder2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

                if(intent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(intent, 10);

                }
            }
        });






    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case 10:

                if(resultCode==RESULT_OK&&data!=null){
                    final ArrayList<String > results= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    MainActivity.analyze(results);
                }
                break;


        }

    }
    private void color_button(final LinearLayout l,final int c,final String room){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int x;
                if(room.equals("kitch")) {

                    if (c == 1)
                        l.setBackgroundResource(R.drawable.kitchengreen);
                    else if (c == 2)
                        l.setBackgroundResource(R.drawable.kitchenorange);
                    else if (c == 3)
                        l.setBackgroundResource(R.drawable.kitchenred);

                }
                if(room.equals("bed")) {

                    if (c == 1)
                        l.setBackgroundResource(R.drawable.bedgreen);
                    else if (c == 2)
                        l.setBackgroundResource(R.drawable.bedorange);
                    else if (c == 3)
                        l.setBackgroundResource(R.drawable.bedred);

                }
                if(room.equals("sal")) {

                    if (c == 1)
                        l.setBackgroundResource(R.drawable.salgreen);
                    else if (c == 2)
                        l.setBackgroundResource(R.drawable.salorange);
                    else if (c == 3)
                        l.setBackgroundResource(R.drawable.salred);

                }
                if(room.equals("bath")) {

                    if (c == 1)
                        l.setBackgroundResource(R.drawable.toigreen);
                    else if (c == 2)
                        l.setBackgroundResource(R.drawable.toiorange);
                    else if (c == 3)
                        l.setBackgroundResource(R.drawable.toired);

                }


            }
        });






    }





    }











