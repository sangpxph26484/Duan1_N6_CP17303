package com.example.duan1_n6_cp17303;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class ManHinhChaoActivity extends AppCompatActivity {
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        layout = findViewById(R.id.id_constraintlayout);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(ManHinhChaoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}