package com.example.android.healthcareapp.Controllers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.healthcareapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                    Intent intent1 = new Intent(StartActivity.this, MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent1);
                    finish();

            }
        },3000);
    }
}