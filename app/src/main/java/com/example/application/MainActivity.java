package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.main_start_btn);

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this.R.anim.bounce);
                        startBtn.startAnimation(animation);

              Intent  intent = new Intent( MainActivity.this, GameActivity.class);
                MainActivity.this.startActivities(intent);

            }
        });

    }
}

