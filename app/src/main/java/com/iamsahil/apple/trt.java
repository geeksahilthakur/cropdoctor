package com.iamsahil.apple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class trt extends AppCompatActivity {

    soup.neumorphism.NeumorphCardView crd0;
    RelativeLayout r1;
    RelativeLayout r2;
    RelativeLayout r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trt);


        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);

        r1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), video.class);
                String as = "https://firebasestorage.googleapis.com/v0/b/playlist-8bb2b.appspot.com/o/video%2Fapscb.mp4?alt=media&token=00604af8-f1d5-4592-be9b-0b356242e342";
                String ds = "https://www.planetnatural.com/pest-problem-solver/plant-disease/apple-scab/";
                i.putExtra("A", as);
                i.putExtra("D", ds);
                startActivity(i);

            }
        });

        r2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), video.class);
                String as = "https://firebasestorage.googleapis.com/v0/b/playlist-8bb2b.appspot.com/o/video%2Fls.mp4?alt=media&token=bd0db44e-b3b7-46c7-bc36-5d2f1a2778ab";
                String ds = "https://www.davey.com/insect-disease-resource-center/apple-scab/";
                i.putExtra("A", as);
                i.putExtra("D", ds);
                startActivity(i);

            }
        });

        r3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), video.class);
                String as = "https://firebasestorage.googleapis.com/v0/b/playlist-8bb2b.appspot.com/o/video%2Fstemcnk.mp4?alt=media&token=deffe644-4b93-492f-993a-1e685e2772ee";
                String ds = "https://homeguides.sfgate.com/cure-canker-fungus-apple-tree-60121.html";
                i.putExtra("A", as);
                i.putExtra("D", ds);
                startActivity(i);

            }
        });


    }
}