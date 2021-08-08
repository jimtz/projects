package com.example.electricalpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.lang.String;
import java.io.*;
import java.util.Arrays;
import java.util.Random;



public class MainActivity extends AppCompatActivity {
    MediaPlayer danger_song,bravo_song;
    public static boolean b_lights ;
    public static boolean b_bathroom ;
    public static boolean b_bed;
    public static boolean b_sofa ;
    public static boolean b_kitchen ;
    public static boolean b_therm=false ;
    public static boolean b_furn;
    public static boolean run=false;
    public static boolean run2=false;
    public static ImageButton powerBtn;
    static ImageButton therm ;
    static ImageButton bathroom ;
    static ImageButton bed ;
    static ImageButton sofa;
    static ImageButton kitchen;
    static ImageButton furn ;
    static ImageButton clock;
    MediaPlayer water_ready;

    static ArrayList<String> kitchen_ar =new ArrayList<String>();
    static ArrayList<String> sofa_ar =new ArrayList<String>();
    static ArrayList<String> bed_ar =new ArrayList<String>();
    static ArrayList<String> devices_ar =new ArrayList<String>();
    static ArrayList<String> bath_ar =new ArrayList<String>();
    static ArrayList<String> sound_ar =new ArrayList<String>();
    static ArrayList<String> shower_ar =new ArrayList<String>();
    public static int cbath=0,ckitch=0,csal=0,cbed=0;


    public static TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final Intent intent = new Intent(getApplicationContext(),AdvancedSettingsActivity.class);
        final Intent intent2 = new Intent(getApplicationContext(),TimeActivity.class);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // no animation (api 5)
        startActivity(intent);

        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // no animation (api 5)
        startActivity(intent2);

        read();

           powerBtn = (ImageButton) findViewById(R.id.powerBtn);



            text = (TextView) findViewById(R.id.text);
            ImageButton settings = findViewById(R.id.settings);
            therm = (ImageButton) findViewById(R.id.therm);
            bathroom = (ImageButton) findViewById(R.id.bathroom);
            bed = (ImageButton) findViewById(R.id.bed);
            sofa = (ImageButton) findViewById(R.id.sofa);
            kitchen = (ImageButton) findViewById(R.id.kitchen);
            furn = (ImageButton) findViewById(R.id.furn);
            clock = (ImageButton) findViewById(R.id.clock);
            b_lights = false;
            b_bathroom = false;
            b_bed = false;
            b_sofa = false;
            b_kitchen = false;
            b_furn = false;
            run=false;
            run2=false;
            bathroom.setBackgroundResource(R.drawable.bathroom);
            bed.setBackgroundResource(R.drawable.bed);
            sofa.setBackgroundResource(R.drawable.sal);
            kitchen.setBackgroundResource(R.drawable.kitchen);
            bathroom.setBackgroundResource(R.drawable.toi);
            furn.setBackgroundResource(R.drawable.oven);
            therm.setBackgroundResource(R.drawable.bathroom);



        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        if (b_lights){
                            b_lights=false;
                            b_bathroom = false;
                            b_bed = false;
                            b_sofa = false;
                            b_kitchen = false;
                            b_therm=false;
                            b_furn=false;
                            text.setText("");
                            change(false);
                        }else{
                            b_lights=true;
                            b_bathroom = true;
                            b_bed = true;
                            b_sofa = true;
                            b_kitchen = true;
                            b_furn=true;
                            text.setText(texting());
                            bathroom.setBackgroundResource(R.drawable.toi22);
                            bed.setBackgroundResource(R.drawable.bed22);
                            sofa.setBackgroundResource(R.drawable.sal22);
                            kitchen.setBackgroundResource(R.drawable.kitchen22);
                            bathroom.setBackgroundResource(R.drawable.toi22);
                            furn.setBackgroundResource(R.drawable.oven22);
                            change(true);

                        }
                    }
                }).start();



            }
        });

        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b_lights){
                    if(b_bathroom){
                        b_bathroom = false;
                        bathroom.setBackgroundResource(R.drawable.toi);
                        if(TimeActivity.open)
                            TimeActivity.bath_button.setBackgroundResource(R.drawable.toi);
                    }else{
                        b_bathroom=true;
                        bathroom.setBackgroundResource(R.drawable.toi22);
                        if(TimeActivity.open)
                            TimeActivity.bath_button.setBackgroundResource(R.drawable.toi22);
                    }
                    text.setText(texting());
                }
            }
        });

        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b_lights){
                    if(b_bed){
                        b_bed = false;
                        bed.setBackgroundResource(R.drawable.bed);
                        if(TimeActivity.open)
                            TimeActivity.bed_button.setBackgroundResource(R.drawable.bed);
                    }else{
                        b_bed=true;
                        bed.setBackgroundResource(R.drawable.bed22);
                        if(TimeActivity.open)
                            TimeActivity.bed_button.setBackgroundResource(R.drawable.bed22);
                    }
                   text.setText(texting());
                }
            }
        });

        sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b_lights){
                    if(b_sofa){
                        b_sofa = false;
                        sofa.setBackgroundResource(R.drawable.sal);
                        if(TimeActivity.open)
                            TimeActivity.sofa_button.setBackgroundResource(R.drawable.sal);
                    }else{
                        b_sofa=true;
                        sofa.setBackgroundResource(R.drawable.sal22);
                        if(TimeActivity.open)
                            TimeActivity.sofa_button.setBackgroundResource(R.drawable.sal22);
                    }
                  text.setText(texting());
                }
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b_lights){
                    if(b_kitchen){
                        b_kitchen = false;
                        kitchen.setBackgroundResource(R.drawable.kitchen);
                        if(TimeActivity.open)
                            TimeActivity.kitchen_button.setBackgroundResource(R.drawable.kitchen);
                    }else{
                        b_kitchen=true;
                        kitchen.setBackgroundResource(R.drawable.kitchen22);
                        if(TimeActivity.open)
                            TimeActivity.kitchen_button.setBackgroundResource(R.drawable.kitchen);
                    }
                  text.setText(texting());
                }
            }
        });

        furn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b_lights){
                    if(b_furn){
                        b_furn = false;
                        furn.setBackgroundResource(R.drawable.oven);
                    }else{
                        b_furn=true;
                        furn.setBackgroundResource(R.drawable.oven22);
                    }
                   text.setText(texting());
                }
            }
        });



        therm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b_lights){
                    if(b_therm){
                        b_therm=false;
                        therm.setBackgroundResource(R.drawable.bathroom);
                    }else{
                        b_therm=true;
                        therm.setBackgroundResource(R.drawable.bathroom22);
                        new Thread(new Runnable() {
                            public void run() {
                                water_ready=MediaPlayer.create(MainActivity.this,R.raw.water);
                                Random r =new Random();
                                int x=r.nextInt(10)+4;
                                texting(x);
                                boolean get_out=false;
                                while(x>0) {

                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        System.out.println("Can't sleep");
                                    }
                                    x--;
                                    texting(x);
                                    if(!b_therm){
                                        break;
                                    }

                                }
                                if(x==0){
                                    water_ready.start();
                                    try {
                                        TimeUnit.SECONDS.sleep(2);
                                    } catch (InterruptedException e) {
                                        System.out.println("Can't sleep");
                                    }
                                    water_ready.stop();
                                }
                                b_therm = false;
                                therm.setBackgroundResource(R.drawable.bathroom);
                                text.setText(texting());


                            }
                        }).start();

                    }
                }else{
                    b_therm=false;
                    therm.setBackgroundResource(R.drawable.bathroom);
                }
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // b_therm=false; //close the water heater
                startActivity(intent);

            }
        });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //b_therm=false; //close the water heater
                startActivity(intent2);

            }
        });


        ImageButton recorder = (ImageButton) findViewById(R.id.record);
        recorder.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

                if(intent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(intent, 10);

                }
            }
        });

       new Thread(new Runnable() { //changes the colors according to the kw they are consuming
            public void run() {

                int kw_bath = 0, kw_bed = 0, kw_sal = 0, kw_kitch = 0, countR, countG;

                danger_song = MediaPlayer.create(MainActivity.this, R.raw.bell);
                bravo_song = MediaPlayer.create(MainActivity.this, R.raw.bravo);
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(12);
                    } catch (InterruptedException e) {
                        System.out.println("Can't sleep");
                    }

                    countR = 0;
                    countG = 0;
                    if (MainActivity.b_lights) {
                        Random r = new Random();
                        kw_bath = r.nextInt(100);
                        if (!MainActivity.b_bathroom)
                            kw_bath = 0;
                        kw_bed = r.nextInt(100);
                        if (!MainActivity.b_bed)
                            kw_bed = 0;
                        kw_sal = r.nextInt(100);
                        if (!MainActivity.b_sofa)
                            kw_sal = 0;
                        kw_kitch = r.nextInt(100);
                        if ((!MainActivity.b_furn) || (!MainActivity.b_kitchen))
                            kw_kitch = r.nextInt(2) == 0 ? 0 : kw_kitch;

                    } else {
                        kw_bath = 0;
                        kw_bed = 0;
                        kw_sal = 0;
                        kw_kitch = 0;

                    }
                    if (kw_bath < 33) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.bath2.setBackgroundResource(R.drawable.toigreen);
                                cbath=1;
                            }
                        });
                        countG++;

                    } else if (kw_bath < 66) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.bath2.setBackgroundResource(R.drawable.toiorange);
                                cbath=2;
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.bath2.setBackgroundResource(R.drawable.toired);
                                cbath=3;
                            }
                        });

                        countR++;

                    }
                    if (kw_bed < 33) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.bed2.setBackgroundResource(R.drawable.bedgreen);
                                cbed=1;
                            }
                        });

                        countG++;

                    } else if (kw_bed < 66) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.bed2.setBackgroundResource(R.drawable.bedorange);
                                cbed=2;
                            }
                        });


                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.bed2.setBackgroundResource(R.drawable.bedred);
                                cbed=3;
                            }
                        });

                        countR++;
                    }
                    if (kw_sal < 33) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.sal2.setBackgroundResource(R.drawable.salgreen);
                                csal=1;
                            }
                        });

                        countG++;

                    } else if (kw_sal < 66) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.sal2.setBackgroundResource(R.drawable.salorange);
                                csal=2;
                            }
                        });


                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.sal2.setBackgroundResource(R.drawable.salred);
                                csal=3;
                            }
                        });

                        countR++;

                    }
                    if (kw_kitch < 33) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.kitch2.setBackgroundResource(R.drawable.kitchengreen);
                                ckitch=1;
                            }
                        });

                        countG++;

                    } else if (kw_kitch < 66) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.kitch2.setBackgroundResource(R.drawable.kitchenorange);
                                ckitch=2;
                            }
                        });


                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdvancedSettingsActivity.kitch2.setBackgroundResource(R.drawable.kitchenred);
                                ckitch=3;
                            }
                        });

                        countR++;
                    }




                    if (MainActivity.b_lights && MainActivity.run2 && countR >= 2)
                        danger_song.start();
                    else if (MainActivity.b_lights && MainActivity.run2 && countG >= 2)
                        bravo_song.start();





                }


            }

        }).start();




    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case 10:

                if(resultCode==RESULT_OK&&data!=null){
                   String s=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                   final ArrayList<String >results=new ArrayList<String>(Arrays.asList(s.split(" ")));
                   analyze(results);

                }
                break;


        }

    }












    public static String  texting(){

       return "Bathroom    "+ ((b_bathroom)? "ON": "OFF" )+"\nBedroom     "+ ((b_bed)? "ON": "OFF" ) +" \nLiving Room "+((b_sofa)? "ON": "OFF" )+" \nKitchen     "+(((b_kitchen)? "ON": "OFF")+"\nKitchen Devices "+((b_furn)? "ON": "OFF" ) +"\n"+"WaterHeater "+((b_therm)?"ON":"OFF")+"\n");

    }

    public static void texting(int sec){

        if(sec!=0) {
            text.setText(texting() + "\n The Water Will Be Warm in " + sec + " minutes\n");
        }else {
             text.setText(texting() + "\n The Water Is Warm ! Enjoy your shower!\n");
        }

    }







    public static void change(boolean c){
        if(!c) {
            bathroom.setBackgroundResource(R.drawable.toi);
            bed.setBackgroundResource(R.drawable.bed);
            sofa.setBackgroundResource(R.drawable.sal);
            kitchen.setBackgroundResource(R.drawable.kitchen);
            bathroom.setBackgroundResource(R.drawable.toi);
            furn.setBackgroundResource(R.drawable.oven);

        }else{
            bathroom.setBackgroundResource(R.drawable.toi22);
            bed.setBackgroundResource(R.drawable.bed22);
            sofa.setBackgroundResource(R.drawable.sal22);
            kitchen.setBackgroundResource(R.drawable.kitchen22);
            bathroom.setBackgroundResource(R.drawable.toi22);
            furn.setBackgroundResource(R.drawable.oven22);



        }

    }
    public static void change(String x,boolean c){


        if(x.equals("Bath")) {
            if (c)
                bathroom.setBackgroundResource(R.drawable.toi22);
            else
                bathroom.setBackgroundResource(R.drawable.toi);
        }else if(x.equals("Bed")){
            if (c)
                bed.setBackgroundResource(R.drawable.bed22);
            else
                bed.setBackgroundResource(R.drawable.bed);
        }else if(x.equals("Sal")){
            if (c)
                sofa.setBackgroundResource(R.drawable.sal22);
            else
                sofa.setBackgroundResource(R.drawable.sal);

        }else if(x.equals("Kitch")){
            if (c)
                kitchen.setBackgroundResource(R.drawable.kitchen22);
            else
                kitchen.setBackgroundResource(R.drawable.kitchen);


        }
    }

    public static void analyze(final ArrayList<String> results) {


        for (int i = 0, l = results.size(); i < l; ++i) {
            results.set(i, results.get(i).toLowerCase());
        }
        if ((results.contains("activate")||results.contains("set") )&& TimeActivity.open) {
            button_activate(results);

        }else{
            button_activate(results,kitchen_ar,kitchen);
            button_activate(results,bath_ar,bathroom);
            button_activate(results,bed_ar,bed);
            button_activate(results,devices_ar,furn);
            button_activate(results,sofa_ar,sofa);
            button_activate(results,shower_ar,therm);
            if (AdvancedSettingsActivity.open)
                button_activate(results, sound_ar, AdvancedSettingsActivity.green);

        }


    }
        private static boolean search(ArrayList<String> talk,ArrayList<String> list){
            for(String x:list)
                if(talk.contains(x))
                    return true;

            return false;


        }



        private static void button_activate(ArrayList<String> talk){
            boolean found=false;
            if(run) {
                run=false;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Can't sleep");
                }
            }
            if(search(talk,bath_ar)){
                found=true;
                TimeActivity.bath_button.performClick();
            }else if(search(talk,sofa_ar)){
                found=true;
                TimeActivity.sofa_button.performClick();
            }else if(search(talk,kitchen_ar)){
                found=true;
                TimeActivity.kitchen_button.performClick();
            }else if(search(talk,bed_ar)){
                found=true;
                TimeActivity.bed_button.performClick();
            }
            if(found){


                ArrayList<String> words=new ArrayList<String>();
                words.add(null);words.add("one"); words.add("two"); words.add("three"); words.add("four"); words.add("five"); words.add("six"); words.add("seven"); words.add("eight"); words.add("nine"); words.add("ten"); words.add("eleven"); words.add("twelve");
                words.add("1");words.add("2");words.add("3");words.add("4");words.add("5");words.add("6");words.add("7");words.add("8");words.add("9");words.add("10");words.add("11");words.add("12");
                help(talk,words,1,TimeActivity.bt1);
                help(talk,words,2,TimeActivity.bt2);
                help(talk,words,3,TimeActivity.bt3);
                help(talk,words,4,TimeActivity.bt4);
                help(talk,words,5,TimeActivity.bt5);
                help(talk,words,6,TimeActivity.bt6);
                help(talk,words,7,TimeActivity.bt7);
                help(talk,words,8,TimeActivity.bt8);
                help(talk,words,9,TimeActivity.bt9);
                help(talk,words,10,TimeActivity.bt10);
                help(talk,words,11,TimeActivity.bt11);
                help(talk,words,12,TimeActivity.bt12);
                help(talk,words,13,TimeActivity.bt1);
                help(talk,words,14,TimeActivity.bt2);
                help(talk,words,15,TimeActivity.bt3);
                help(talk,words,16,TimeActivity.bt4);
                help(talk,words,17,TimeActivity.bt5);
                help(talk,words,18,TimeActivity.bt6);
                help(talk,words,19,TimeActivity.bt7);
                help(talk,words,20,TimeActivity.bt8);
                help(talk,words,21,TimeActivity.bt9);
                help(talk,words,22,TimeActivity.bt10);
                help(talk,words,23,TimeActivity.bt11);
                help(talk,words,24,TimeActivity.bt12);
                first=0;
                second=0;


            }



    }
    public static int first=0,second=0;

    private static void help(ArrayList<String> talk,ArrayList<String> words,int x,Button b){
        if(second!=0)
            return;
        if(talk.contains(words.get(x))){
            if(x>12)
                x=x-12;

            if(first==0) {
                first = x;

                b.performClick();
            }else {
                second = x;
                b.performClick();
                System.out.println("START: "+first+" END: "+second);
                TimeActivity.start.performClick();

            }
        }


    }




    private static void button_activate(ArrayList<String> talk,ArrayList<String> words,ImageButton button){

        for(String w:words)
            if(talk.contains(w))
                button.performClick();

    }



    private void read(){
        organize("bedroom.txt", bed_ar);
        organize("kitchen.txt",kitchen_ar);
        organize("bathroom.txt",bath_ar);
        organize("devices.txt", devices_ar);
        organize("living_room.txt",sofa_ar);
        organize("shower.txt",shower_ar);
        organize("sound.txt", sound_ar);


            //Toast.makeText(MainActivity.this, "Saved your text", Toast.LENGTH_LONG).show();

    }



    private void organize(String path,ArrayList<String> list){
        String data;
        try{
        InputStream is =getAssets().open(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader((is)));
        while((data=reader.readLine())!=null){
                data=data.trim().toLowerCase();
                list.add(data);
            }
            is.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }










}
