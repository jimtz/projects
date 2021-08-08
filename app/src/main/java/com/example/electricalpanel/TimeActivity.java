package com.example.electricalpanel;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class TimeActivity extends AppCompatActivity {
    protected static TextView text;

    public static Button bt1;
    public static Button bt2;
    public static Button bt3;
    public static Button bt4;
    public static Button bt5;
    public static Button bt6;
    public static Button bt7;
    public static Button bt8;
    public static Button bt9;
    public static Button bt10;
    public static Button bt11;
    public static Button bt12;
    public static  ImageButton home;
    public static ImageButton recorder3 ;
    public static ImageButton bed_button ;
    public static ImageButton bath_button ;
    public static ImageButton sofa_button ;
    public static ImageButton kitchen_button ;
    private static boolean block=false;
    public static Button start;
    protected ImageButton powerBtn;
    public static boolean begg=true,open=false;
    public int beg_bed=-1,end_bed=13,beg_kitch=-1,end_kitch=13,beg_bath=-1,end_bath=13,beg_sal=-1,end_sal=13;



    protected void onCreate(Bundle savedInstanceState2) {

        super.onCreate(savedInstanceState2);
        setContentView(R.layout.time_activity);

        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        bt3=findViewById(R.id.bt3);
        bt4=findViewById(R.id.bt4);
        bt5=findViewById(R.id.bt5);
        bt6=findViewById(R.id.bt6);
        bt7=findViewById(R.id.bt7);
        bt8=findViewById(R.id.bt8);
        bt9=findViewById(R.id.bt9);
        bt10=findViewById(R.id.bt10);
        bt11=findViewById(R.id.bt11);
        bt12=findViewById(R.id.bt12);
        start=findViewById(R.id.start);
        if(!MainActivity.b_lights)
            start.setClickable(false);

        powerBtn = (ImageButton) findViewById(R.id.powerBtn);
        text = (TextView) findViewById(R.id.text);
        home = (ImageButton) findViewById(R.id.home);
        recorder3 = findViewById(R.id.record3);
        bed_button = (ImageButton) findViewById(R.id.bed);
        bath_button = (ImageButton) findViewById(R.id.bathroom);
        sofa_button = (ImageButton) findViewById(R.id.sofa);
        kitchen_button = (ImageButton) findViewById(R.id.kitchen);

        if(!open) {
            open = true;
            overridePendingTransition(0,0);
            onBackPressed();

        }

        if(MainActivity.b_lights)
            change(true);
        else
            change(false);
		
		if(MainActivity.run)
			start.setText("STOP");
		else
			start.setText("START");



        small_button_control(false);
        big_button_control(true);

        if(MainActivity.b_lights) {
            text.setText(MainActivity.texting() + "1)Choose the room!\n2)Enter the hour it goes ON & OFF\n3)Activate Auto!");
            big_button_control(true);
            if(MainActivity.b_kitchen)
                change("Kitch",true);
            else
                change("Kitch",false);
            if(MainActivity.b_bed)
                change("Bed",true);
            else
                change("Bed",false);
            if(MainActivity.b_bathroom)
                change("Bath",true);
            else
                change("Bath",false);
            if(MainActivity.b_sofa)
                change("Sal",true);
            else
                change("Sal",false);

        }else {
            change(false);
            big_button_control(false);
            text.setText("");
        }

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
                        big_button_control(false);
                        start.setClickable(false);
                        MainActivity.change(false);
                        change(false);


                    }else{
                        MainActivity.b_lights=true;
                        MainActivity.b_bathroom = true;
                        MainActivity.b_bed = true;
                        MainActivity.b_sofa = true;
                        MainActivity.b_kitchen = true;
                        big_button_control(true);
                        text.setText(MainActivity.texting()+"1)Choose the room!\n2)Enter the hour it goes ON & OFF\nPress START");
                        MainActivity.text.setText(MainActivity.texting());
                        MainActivity.change(true);
                        start.setClickable(true);
                        change(true);
                    }


            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!block) {
                    if (MainActivity.run == false) {
                        new Thread(new Runnable() {//changes the values
                            public void run() {
                                if (MainActivity.b_lights) {
                                    MainActivity.run = true;
                                    big_button_control(false);
                                    text.setText("Auto mode   ON!");
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        System.out.println("Can't sleep");
                                    }
                                    text.setText(MainActivity.texting());
                                    start.setText("STOP");
                                    recorder3.setClickable(true);
                                    while (true) {
                                        int sec = 1;
                                        while (sec <= 12) {

                                            if (beg_bath == sec) {
                                                MainActivity.b_bathroom = true;
                                                MainActivity.change("Bath", true);
                                                change("Bath", true);

                                            } else if (end_bath == sec) {
                                                MainActivity.b_bathroom = false;
                                                MainActivity.change("Bath", false);
                                                change("Bath", false);

                                            } else if (sec < beg_bath) {
                                                MainActivity.b_bathroom = false;
                                                MainActivity.change("Bath", false);
                                                change("Bath", false);

                                            }

                                            if (beg_bed == sec) {
                                                MainActivity.b_bed = true;
                                                MainActivity.change("Bed", true);
                                                change("Bed", true);
                                            } else if (end_bed == sec) {
                                                MainActivity.b_bed = false;
                                                MainActivity.change("Bed", false);
                                                change("Bed", false);
                                            } else if (sec < beg_bed) {
                                                MainActivity.b_bed = false;
                                                MainActivity.change("Bed", false);
                                                change("Bed", false);
                                            }

                                            if (beg_sal == sec) {
                                                MainActivity.b_sofa = true;
                                                MainActivity.change("Sal", true);
                                                change("Sal", true);
                                            } else if (end_sal == sec) {
                                                MainActivity.b_sofa = false;
                                                MainActivity.change("Sal", false);
                                                change("Sal", false);
                                            } else if (sec < beg_sal) {
                                                MainActivity.b_sofa = false;
                                                MainActivity.change("Sal", false);
                                                change("Sal", false);
                                            }

                                            if (beg_kitch == sec) {
                                                MainActivity.b_kitchen = true;
                                                MainActivity.change("Kitch", true);
                                                change("Kitch", true);
                                            } else if (end_kitch == sec) {
                                                MainActivity.b_kitchen = false;
                                                MainActivity.change("Kitch", false);
                                                change("Kitch", false);
                                            } else if (sec < beg_kitch) {
                                                MainActivity.b_kitchen = false;
                                                MainActivity.change("Kitch", false);
                                                change("Kitch", false);
                                            }

                                            text.setText(MainActivity.texting() + "\nHour: " + sec + ":00");
                                            MainActivity.text.setText(MainActivity.texting() + "\nHour: " + sec + ":00");

                                            if (MainActivity.run == false || !MainActivity.b_lights)
                                                break;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException e) {
                                                System.out.println("Can't sleep");
                                            }
                                            if (MainActivity.run == false || !MainActivity.b_lights)
                                                break;
                                            sec++;
                                        }
                                        if (MainActivity.run == false || !MainActivity.b_lights)
                                            break;

                                    }
                                    big_button_control(true);
                                    if (MainActivity.b_lights) {
                                        text.setText("Auto mode   OFF!");
                                        start.setText("START");
                                        MainActivity.text.setText(MainActivity.texting());
                                        try {
                                            TimeUnit.SECONDS.sleep(1);
                                        } catch (InterruptedException e) {
                                            System.out.println("Can't sleep");
                                        }
                                        text.setText(MainActivity.texting());
                                    }

                                }
                            }
                        }).start();
                    } else {
                        MainActivity.run = false;
                    }
                }else{
                    text.setText(MainActivity.texting()+"1)Choose the room!\n2)Enter the hour it goes ON & OFF\nPress START");
                    MainActivity.text.setText(MainActivity.texting());
                    powerBtn.setClickable(true);
                    big_button_control(true);
                    block=false;
                    start.setText("START");
                    small_button_control(false);
                    home.setClickable(true);
                }
            }
        });





        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(0,0);

            }
        });





        bed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.b_lights) {
                    small_button_control(true);
                    home.setClickable(false);
                    big_button_control(false);
                    powerBtn.setClickable(false);
                    block=true;
                    start.setText("Cancel");
                    text.setText(MainActivity.texting() + "Enter the hour you want to power ON the bedroom");
                    foo(bt1, "bedroom", 1);
                    foo(bt2, "bedroom", 2);
                    foo(bt3, "bedroom", 3);
                    foo(bt4, "bedroom", 4);
                    foo(bt5, "bedroom", 5);
                    foo(bt6, "bedroom", 6);
                    foo(bt7, "bedroom", 7);
                    foo(bt8, "bedroom", 8);
                    foo(bt9, "bedroom", 9);
                    foo(bt10, "bedroom", 10);
                    foo(bt11, "bedroom", 11);
                    foo(bt12, "bedroom", 12);

                }
            }
        });
        bath_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.b_lights){
                    small_button_control(true);
                    home.setClickable(false);
                    big_button_control(false);
                    home.setClickable(false);
                    block=true;
                    start.setText("Cancel");
                    powerBtn.setClickable(false);
                    text.setText(MainActivity.texting()+"Enter the hour you want to power ON the bathroom");
                    foo(bt1,"bathroom",1);
                    foo(bt2,"bathroom",2);
                    foo(bt3,"bathroom",3);
                    foo(bt4,"bathroom",4);
                    foo(bt5,"bathroom",5);
                    foo(bt6,"bathroom",6);
                    foo(bt7,"bathroom",7);
                    foo(bt8,"bathroom",8);
                    foo(bt9,"bathroom",9);
                    foo(bt10,"bathroom",10);
                    foo(bt11,"bathroom",11);
                    foo(bt12,"bathroom",12);
                }
            }
        });
       kitchen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.b_lights) {
                    small_button_control(true);
                    home.setClickable(false);
                    big_button_control(false);
                    powerBtn.setClickable(false);
                    block=true;
                    start.setText("Cancel");
                    text.setText(MainActivity.texting() + "Enter the hour you want to power ON the kitchen");
                    foo(bt1, "kitchen", 1);
                    foo(bt2, "kitchen", 2);
                    foo(bt3, "kitchen", 3);
                    foo(bt4, "kitchen", 4);
                    foo(bt5, "kitchen", 5);
                    foo(bt6, "kitchen", 6);
                    foo(bt7, "kitchen", 7);
                    foo(bt8, "kitchen", 8);
                    foo(bt9, "kitchen", 9);
                    foo(bt10, "kitchen", 10);
                    foo(bt11, "kitchen", 11);
                    foo(bt12, "kitchen", 12);
                }
            }
        });
        sofa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.b_lights){
                    small_button_control(true);
                    home.setClickable(false);
                    big_button_control(false);
                    powerBtn.setClickable(false);
                    block=true;
                    start.setText("Cancel");
                    text.setText(MainActivity.texting()+"Enter the hour you want to power ON the Living Room");
                    foo(bt1,"living room",1);
                    foo(bt2,"living room",2);
                    foo(bt3,"living room",3);
                    foo(bt4,"living room",4);
                    foo(bt5,"living room",5);
                    foo(bt6,"living room",6);
                    foo(bt7,"living room",7);
                    foo(bt8,"living room",8);
                    foo(bt9,"living room",9);
                    foo(bt10,"living room",10);
                    foo(bt11,"living room",11);
                    foo(bt12,"living room",12);

            }
        }
        });
        recorder3 = findViewById(R.id.record3);
        recorder3.setOnClickListener(new View.OnClickListener() {

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
    private void foo(Button b,final String s,final int  hour){

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int starts=0,end=0;
                if(begg) {

                    starts = hour;
                    begg=false;
                    if(s.equals("bedroom")){
                        beg_bed=starts;
                    }else if(s.equals("kitchen")){
                        beg_kitch=starts;
                    }else if(s.equals("bathroom")){
                        beg_bath=starts;
                    }else{
                        beg_sal=starts;
                    }
                    text.setText(MainActivity.texting()+"Enter the hour you want to power OFF the "+s);
                }else {
                    if(s.equals("bedroom")){
                        starts=beg_bed;
                    }else if(s.equals("kitchen")){
                        starts=beg_kitch;
                    }else if(s.equals("bathroom")){
                        starts=beg_bath;
                    }else{
                        starts=beg_sal;
                    }

                    end = hour;
                    begg=true;
                    if(starts>end){
                        int temp=starts;
                        starts=end;
                        end=temp;
                    }else if(starts==end){
                        starts=1;
                        end=12;
                    }

                    text.setText(MainActivity.texting()+"The "+s+" will be On from "+starts+" to "+end+"o'clock ");
                    big_button_control(true);
                    powerBtn.setClickable(true);
                    start.setClickable(true);
                    small_button_control(false);
                    if(s.equals("bedroom")){
                        end_bed=end;
                    }else if(s.equals("kitchen")){
                        end_kitch=end;
                    }else if(s.equals("bathroom")){
                        end_bath=end;
                    }else{
                        end_sal=end;
                    }
                    block=false;
                    start.setText("START");
                    small_button_control(false);
                    home.setClickable(true);

                }
            }

        });


    }

    public void small_button_control(boolean cond){

            bt1.setClickable(cond);
            bt2.setClickable(cond);
            bt3.setClickable(cond);
            bt4.setClickable(cond);
            bt5.setClickable(cond);
            bt6.setClickable(cond);
            bt7.setClickable(cond);
            bt8.setClickable(cond);
            bt9.setClickable(cond);
            bt10.setClickable(cond);
            bt11.setClickable(cond);
            bt12.setClickable(cond);


    }
    public void big_button_control(boolean cond){

            bath_button.setClickable(cond);
            bed_button.setClickable(cond);
            sofa_button.setClickable(cond);
            kitchen_button.setClickable(cond);
            recorder3.setClickable(cond);


    }


    public static void change(boolean c){
        if(!c) {

            bath_button.setBackgroundResource(R.drawable.toi);
            bed_button.setBackgroundResource(R.drawable.bed);
            sofa_button.setBackgroundResource(R.drawable.sal);
            kitchen_button.setBackgroundResource(R.drawable.kitchen);



        }else{
            bath_button.setBackgroundResource(R.drawable.toi22);
            bed_button.setBackgroundResource(R.drawable.bed22);
            sofa_button.setBackgroundResource(R.drawable.sal22);
            kitchen_button.setBackgroundResource(R.drawable.kitchen22);

        }

    }
    public static void change(String x,boolean c){
        if(x.equals("Bath")) {
            if (c)
                bath_button.setBackgroundResource(R.drawable.toi22);
            else
                bath_button.setBackgroundResource(R.drawable.toi);
        }else if(x.equals("Bed")){
            if (c)
                bed_button.setBackgroundResource(R.drawable.bed22);
            else
                bed_button.setBackgroundResource(R.drawable.bed);
        }else if(x.equals("Sal")){
            if (c)
                sofa_button.setBackgroundResource(R.drawable.sal22);
            else
                sofa_button.setBackgroundResource(R.drawable.sal);

        }else if(x.equals("Kitch")){
            if (c)
                kitchen_button.setBackgroundResource(R.drawable.kitchen22);
            else
                kitchen_button.setBackgroundResource(R.drawable.kitchen);


        }


    }
}



